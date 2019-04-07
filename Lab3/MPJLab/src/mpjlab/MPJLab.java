package mpjlab;

import mpi.MPI;

public class MPJLab 
{
    private static final int rootProcess = 0;
    private static final String inputFileName = "data/input.txt";
    private static final int elementsPerProcess = 3;
    
    public static void main(String args[]) throws Exception 
    {
        //Initialize the MPI protocol
        MPI.Init(args);

        //Obtain the rank of the current process running the code
        int currProcess = MPI.COMM_WORLD.Rank();

        if(currProcess == rootProcess){
            //Dispatch a master node 
            MasterNode masterNode = new MasterNode(elementsPerProcess, inputFileName);
            masterNode.Execute();
        }
        else{
            //Dispatch a computational node
            ComputationNode computationNode = new ComputationNode(elementsPerProcess);
            computationNode.Execute();
        }
        
        //Conclude the MPI protocol's work
        MPI.Finalize();
    }
} 
