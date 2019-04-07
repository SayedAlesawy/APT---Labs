package mpjlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import mpi.MPI;

public class MasterNode 
{
    private final int BUFFERMAXSIZE = 100100;
    private final int _rootProcessRank = 0;
    private final int _elementsPerProcess;
    private final int [] _sendBuffer;
    private final int [] _recvBuffer;
    private final String _inputFileName;
    private int _size;
    private int _localSum;
    
    public MasterNode(int elementsPerProcess, String inputFileName)
    {
        _inputFileName = inputFileName;
        _elementsPerProcess = elementsPerProcess;
        _size = 0;
        _sendBuffer = new int[BUFFERMAXSIZE];
        _recvBuffer = new int[BUFFERMAXSIZE];
    }
    
    private void ReadData() 
    {
        File inputFile = new File(_inputFileName);
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            
            try{
                String st;
                _size = 0;
                while ((st = br.readLine()) != null){
                    _sendBuffer[_size] = Integer.parseInt(st);
                    _size++;
                }
            }
            catch(IOException e){
                System.out.println("IO Exception in reading file");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    
    public void CalculateLocalSum()
    {
        _localSum = 0;
        
        for(int i = 0; i < _elementsPerProcess; i++){
           _localSum += _recvBuffer[i];
        }
        
        int currProcess = MPI.COMM_WORLD.Rank();
        System.out.printf("[Process #%d] Local sum = %d\n", currProcess, _localSum);
        
        SendLocalSum();
    }
    
    private void ScatterData()
    {
        MPI.COMM_WORLD.Scatter(_sendBuffer, 0, _elementsPerProcess, MPI.INT, 
                _recvBuffer, 0, _elementsPerProcess, MPI.INT, _rootProcessRank);
    }
    
    private void SendLocalSum()
    {
        _sendBuffer[0] = _localSum;
        
        MPI.COMM_WORLD.Reduce(_sendBuffer, 0, _recvBuffer, 0, 1, MPI.INT, 
                MPI.SUM, _rootProcessRank);
    }
    
    private void CalculateTotalSum()
    {
        int currProcess = MPI.COMM_WORLD.Rank();
        System.out.printf("[Process #%d] Total sum = %d\n", currProcess, _recvBuffer[0]);
    }
    
    public void Execute()
    {
        ReadData();
        
        ScatterData();
        
        CalculateLocalSum();
        
        CalculateTotalSum();
    }
}
