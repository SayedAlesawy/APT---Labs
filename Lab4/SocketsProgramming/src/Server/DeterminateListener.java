import java.io.*;  
import java.net.*; 

public class DeterminateListener extends Thread
{
    private final int portNumber;

    public DeterminateListener(int portNumber)
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
                    new DeterminateRequestHandler(listener.accept(), clientNumber++).start();
                }
            } 
            finally {
                listener.close();
            }
        }
        catch(IOException e){
            System.out.println("Determinate Listener failed to init");
        }
    }

    private static class DeterminateRequestHandler extends Thread
    {
        private final Socket socket;
        private final int clientNumber;

        public DeterminateRequestHandler(Socket socket, int clientNumber)
        {
            this.socket = socket;
            this.clientNumber = clientNumber;

            System.out.printf("New Determinate Request from client # %d\n", this.clientNumber);
        }

        public void run()
        {
            try{
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                try{
                    //Deserialize
                    Matrix matrix = (Matrix)is.readObject();

                    //Do matrix operations
                    MatrixOperations matOps = new MatrixOperations(matrix);
                    int res = matOps.Determinate();

                    //Serialize and send
                    out.println(Integer.toString(res));
                }
                catch(ClassNotFoundException e){
                    System.out.printf("Error handling request of type Determinate from client #%d\n", clientNumber);
                }
            }
            catch(IOException e){
                System.out.printf("Error handling request of type Determinate from client #%d\n", clientNumber);
            }
            finally{
                System.out.printf("Transpose request of Determinate #%d has been answered\n", clientNumber);
                try{
                    socket.close();
                }
                catch(IOException e){
                    System.out.printf("Failed to close socket of type Determinate from client #%d\n", clientNumber);
                }
            }
        }
    }
}
