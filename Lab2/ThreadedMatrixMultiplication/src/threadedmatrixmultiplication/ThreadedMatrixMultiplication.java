package threadedmatrixmultiplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ThreadedMatrixMultiplication {
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
        int contnets[] = new int[n*m]; 
       
        for(int i = 0; i < n*m; i++){
            contnets[i] = sc.nextInt();
        }
        
        mat.set(contnets);
        
        return mat;
    }
    
    public static void main(String[] args) throws IOException {
        //Declaring matrix A
        Matrix A = GetMatrix();
        
        //Declaring matrix B
        Matrix B = GetMatrix();
        
        try{
            Matrix C = A.multiply(B);
            C.print();
        }
        catch(InterruptedException e){
            System.out.printf("Thread was interrupted\n");
        }
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
