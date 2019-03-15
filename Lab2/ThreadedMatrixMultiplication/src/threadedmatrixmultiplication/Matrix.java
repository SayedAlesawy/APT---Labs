package threadedmatrixmultiplication;

public class Matrix 
{
    public int data[][];
    public final int rows;
    public final int cols;
    
    /*A function to intialize a matrix*/
    private void InitializeMatrix()
    {
        data = new int [rows][cols];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j <cols; j++){
                data[i][j] = 0;
            }
        }
    }
    
    /*The Matrix constructor*/
    public Matrix(int n, int m)
    {
        rows = n;
        cols = m;
        InitializeMatrix();
    }
    
    /*A function to set data of a matrix*/
    public boolean set(int[] contents)
    {
        if(contents.length != rows * cols){
            System.out.printf("Not enough values to fill a %d x %d matrix\n", rows, cols);
            return false;
        }
        
        for(int i = 0, j = 0, k = 0; i < contents.length; i++, k++){
            if(i > 0 && i % cols == 0) {
                j++;
                k = 0;
            }
            data[j][k] = contents[i];
        }
        
        return true;
    }
    
    /*A function to print a Matrix*/
    public void print()
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
    
    /*A function to do matrix multiplication*/
    public Matrix multiply(Matrix oth) throws InterruptedException
    {
        if(this.cols != oth.rows){
            System.out.print("Incompatible dimensions for multiplication\n");
            return this;
        }
        
        Matrix res = new Matrix(this.rows, oth.cols);
        
        Thread t1 = new Thread(new Multiply(this, oth, res));
        t1.setName("1");
        
        Thread t2 = new Thread(new Multiply(this, oth, res));
        t2.setName("2");
        
        t1.start(); t2.start();
        t1.join();  t2.join();
        
        return res;
    }
}
