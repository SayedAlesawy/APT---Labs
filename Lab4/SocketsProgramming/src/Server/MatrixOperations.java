import java.lang.IllegalArgumentException;

public class MatrixOperations 
{
    private int [][] data;
    private int rows;
    private int cols;

    public MatrixOperations(Matrix mat)
    {
        this.data = mat.Data();
        this.rows = mat.Rows();
        this.cols = mat.Cols();
    }

    /**A function to swap the dimensions of a matrix */
    private void SwapDimensions()
    {
        int tmp = rows;
        rows = cols;
        cols = tmp;
    }
    
    /**A function to get the cofactors of a matrix */
    private int[][] GetCofactors(int mat[][], int R, int C, int n)
    {
        int cofactors[][] = new int[n-1][n-1];
        int r = 0, c = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != R && j != C){
                    cofactors[r][c] = mat[i][j];
                    c++;
                    if(c == n-1){
                        c = 0;
                        r++;
                    }
                }
            }
        }

        return cofactors;
    }

    /**A helper function that implements the determinate logic*/
    private int GetDeterminate(int mat[][], int n)
    {
        int res = 0;

        if(n == 1) return mat[0][0];

        int sign = 1;

        for(int i = 0; i < n; i++){
            int cofactors[][] = GetCofactors(mat, 0, i, n);
            res += sign * mat[0][i] * GetDeterminate(cofactors, n - 1);
            sign *= -1;
        }

        return res;
    }

    /**A function that gets the transpose of a matrix */
    public Matrix Transpose()
    {
        int transposedMatrix[][] = new int[cols][rows];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols ; j++){
                transposedMatrix[j][i] = data[i][j];
            }
        }
        
        SwapDimensions();
        data = new int[rows][cols];
        data = transposedMatrix;

        Matrix transMatrix = new Matrix(rows, cols);
        transMatrix.Set(data);

        return transMatrix;
    }

    /**A function find the determinate of a matrix*/
    public int Determinate()
    {
        if(rows != cols){
            throw new IllegalArgumentException("Can't find the determinate of a non-square matrix\n");
        }
        
        return GetDeterminate(data, rows);
    }
}