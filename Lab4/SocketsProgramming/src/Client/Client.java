import java.io.*;  
import java.util.*;
import java.net.*;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        Matrix A = GetMatrix();
        int choice = GetChoice();

        A.Print();

        if(choice == 1){
            Matrix ans = SendTransposeRequest(A);
            ans.Print();
        }
        else{
            int det = SendDeterminateRequest(A);
            System.out.printf("Det(A) = %d\n", det);
        }
    }

    private static int SendDeterminateRequest(Matrix mat) throws UnknownHostException, IOException, InterruptedException
    {
        Socket socket = new Socket("localhost", 4000);

        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Serialize matrix
        os.writeObject(mat);
       
        //Sleep for a random interval of time
        Thread.sleep((new Random()).nextInt(10000));
        int ret = 0;

        try{
            String det = in.readLine();

            try{
                ret = Integer.parseInt(det);
            } 
            catch(NumberFormatException e){
                System.out.println("Wrong number formats");
            }

            System.out.println("Received Determinate server response");
        }
        catch(IOException e){
            System.out.println("Failed to receive Transpose response");
        }

        try{
            socket.close();
        }
        catch(IOException e){
            System.out.println("Failed to close socket of type Determinate");
        }

        return ret;
    }

    private static Matrix SendTransposeRequest(Matrix mat) throws UnknownHostException, IOException, InterruptedException
    {
        Socket socket = new Socket("localhost", 3000);

        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

        //Serialize matrix
        os.writeObject(mat);
       
        //Sleep for a random interval of time
        Thread.sleep((new Random()).nextInt(10000));
        Matrix responseMatrix;

        //Deserialize matrix response
        try{
            responseMatrix = (Matrix)is.readObject();
            System.out.println("Received Transpose server response");
        }
        catch(ClassNotFoundException e){
            System.out.println("Failed to receive Transpose response");
            responseMatrix = mat;
        }

        try{
            socket.close();
        }
        catch(IOException e){
            System.out.println("Failed to close socket of type Transpose");
        }

        return responseMatrix;
    }

    private static Matrix GetMatrix() throws IOException 
    {
        Scanner sc = new Scanner(System.in);
        
        int n, m;
        System.out.printf("New Matrix:\n");
        
        System.out.printf("Rows = ");
        n = sc.nextInt();
        
        System.out.printf("Cols = ");
        m = sc.nextInt();
        
        System.out.printf("Enter values: ");
        
        Matrix mat = new Matrix(n, m);
        int contents[][] = new int[n][m]; 
       
        for(int i =0; i < n; i++){
            for(int j = 0; j < m; j++){
                contents[i][j] = sc.nextInt();
            }
        }

        mat.Set(contents);
        
        return mat;
    }

    private static int GetChoice() throws IOException
    {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Enter 1 for Transpose, 2 for Determinate: ");
        int choice = sc.nextInt();

        return choice;
    }

    static class Scanner 
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){  br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException 
        {
                while (st == null || !st.hasMoreTokens()) 
                        st = new StringTokenizer(br.readLine());
                return st.nextToken();
        }

        public double nextDouble() throws NumberFormatException, IOException
        {
                return Double.parseDouble(next());
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public boolean ready() throws IOException {return br.ready();}
    }
}