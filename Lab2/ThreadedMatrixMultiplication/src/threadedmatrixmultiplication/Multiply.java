package threadedmatrixmultiplication;

public class Multiply implements Runnable
{
    private Matrix _A, _B, _C;
    
    /*A function to multiply 2 parts of 2 matrices*/
    private void PerformPartialMultiplication(int c1, int c2)
    {
        int na = _A.rows, ma = _A.cols;
        
        for(int i = 0; i < na; i++){
            for(int j = c1; j < c2; j++){
                _C.data[i][j] = 0;
                for(int k = 0; k < ma; k++){
                    int id = Integer.parseInt(Thread.currentThread().getName());
                    System.out.printf("Thread %d: (%d,%d) = (%d,%d) x (%d,%d)\n", 
                            id, i, j, i, k, k, j);
                    
                    _C.data[i][j] += _A.data[i][k] * _B.data[k][j];
                }
            }
        }
    }
    
    private void SplitWorkLoad()
    {
        switch (Thread.currentThread().getName()) {
            case "1":
                PerformPartialMultiplication(0, _B.cols/2);
                break;
            case "2":
                PerformPartialMultiplication(_B.cols/2, _B.cols);
                break;
            default:
                System.out.printf("Unauthorized thread\n");
                break;
        }
    }
    
    @Override
    public void run()
    {
        SplitWorkLoad();
    }
    
    public Multiply(Matrix A, Matrix B, Matrix C){
        _A = A;
        _B = B;
        _C = C;
    }
}
