import java.io.*;  
import java.net.*; 

public class TransposeListener extends Thread
{
    private final int portNumber;

    public TransposeListener(int portNumber)
    {
        this.portNumber = portNumber;
    }

    public void run() 
    {
        int clientNumber = 0;
        try{
            ServerSocket listener = new ServerSocket(portNumber);
            try{
                while(true){
                    new TransposeRequestHandler(listener.accept(), clientNumber++).start();
                }
            } 
            finally {
                listener.close();
            }
        }
        catch(IOException e){
            System.out.println("Transpose Listener failed to init");
        }
    }

    private static class TransposeRequestHandler extends Thread
    {
        private final Socket socket;
        private final int clientNumber;

        public TransposeRequestHandler(Socket socket, int clientNumber)
        {
            this.socket = socket;
            this.clientNumber = clientNumber;
            
            System.out.printf("New Transpose Request from client # %d\n", this.clientNumber);
        }

        public void run()
        {
            try{
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                try{
                    //Deserialize
                    Matrix matrix = (Matrix)is.readObject();

                    //Do matrix operations
                    MatrixOperations matOps = new MatrixOperations(matrix);
                    Matrix res = matOps.Transpose();

                    //Serialize and send
                    os.writeObject(res);
                }
                catch(ClassNotFoundException e){
                    System.out.printf("Error handling request of type Transpose from client #%d\n", clientNumber);
                }
            }
            catch(IOException e)
            {
                System.out.printf("Error handling request of type Transpose from client #%d\n", clientNumber);
            }
            finally{
                System.out.printf("[Server] Transpose request of client #%d has been answered\n", clientNumber);
                try{
                    socket.close();
                }
                catch(IOException e){
                    System.out.printf("Failed to close socket of type Transpose from client #%d\n", clientNumber);
                }
            }
        }
    }
}