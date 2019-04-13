import java.io.Serializable;

public class Matrix implements Serializable
{
    private static final long serialVersionUID = 123456789L;

    private int data[][];
    private int rows;
    private int cols;
    
    /**The Matrix constructor */
    public Matrix(int n, int m)
    {
        rows = n;
        cols = m;
        InitializeMatrix();
    }
    /**A function to get the number of rows */
    public int Rows()
    {
        return this.rows;
    }

    /**A function to get the number of cols */
    public int Cols()
    {
        return this.cols;
    }

    /**A function to get the internal data in a matrix */
    public int[][] Data()
    {
        return this.data;
    }

    /*A function to intialize a matrix */
    private void InitializeMatrix()
    {
        data = new int [rows][cols];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j <cols; j++){
                data[i][j] = 0;
            }
        }
    }
    
    /**A function to set data of a matrix in a 2D form */
    public void Set(int[][] contents)
    {
        this.data = contents;
    }
    
    /**A function to print a Matrix */
    public void Print()
    {
        System.out.print("Matrix = {\n");
        
        for(int i = 0; i < rows; i++){
            System.out.print("  ");
            
            for(int j = 0; j < cols; j++){
                System.out.printf("%d", data[i][j]);
                if(j + 1 < cols) System.out.print(" ");
            }
            System.out.print("\n");
        }
        
        System.out.print("}\n");
    }
}
