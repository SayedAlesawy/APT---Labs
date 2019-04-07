package mpjlab;

import mpi.MPI;

public class ComputationNode 
{
    private final int BUFFERMAXSIZE;
    private final int _elementsPerProcess;
    private final int _rootProcessRank;
    private final int [] _sendBuffer;
    private final int [] _recvBuffer;
    private int _localSum;
    
    public ComputationNode(int elementsPerProcess)
    {
        BUFFERMAXSIZE = 100100;
        _elementsPerProcess = elementsPerProcess;
        _rootProcessRank = 0;
        _sendBuffer = new int[BUFFERMAXSIZE];
        _recvBuffer = new int[BUFFERMAXSIZE];
    }
    
    private void CalculateLocalSum()
    {
        _localSum = 0;
        for(int i = 0; i < _elementsPerProcess; i++){
           _localSum += _recvBuffer[i];
        }
        
        int currProcess = MPI.COMM_WORLD.Rank();
        System.out.printf("[Process #%d] Local sum = %d\n", currProcess, _localSum);
        
        SendLocalSum();
    }
    
    private void ReceiveChuck()
    {
        int currProcess = MPI.COMM_WORLD.Rank();
        
        MPI.COMM_WORLD.Scatter(_sendBuffer, 0, _elementsPerProcess, MPI.INT, 
                _recvBuffer, 0, _elementsPerProcess, MPI.INT, _rootProcessRank);
        
        System.out.printf("[Process #%d] Data received\n", currProcess);
    }
    
    private void SendLocalSum()
    {
        _sendBuffer[0] = _localSum;
        
        MPI.COMM_WORLD.Reduce(_sendBuffer, 0, _recvBuffer, 0, 1, MPI.INT, 
                MPI.SUM, _rootProcessRank);
    }
    
    public void Execute()
    {
        ReceiveChuck();
        
        CalculateLocalSum();
    }
}
