import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

public class MatrixTest
{
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

        sc.close();
        
        mat.Set(contents);
        
        return mat;
    }
    public static void main(String[] args) throws IOException {
        //Declaring matrix A
        Matrix A = GetMatrix();
        A.Print();

        MatrixOperations matOps = new MatrixOperations(A);

        try{
            int det = matOps.Determinate();
           
            System.out.printf("Det = %d\n", det);
        } catch(Exception e){
            System.out.print(e.getLocalizedMessage());
        }

        A = matOps.Transpose();

        A.Print();
    }
}