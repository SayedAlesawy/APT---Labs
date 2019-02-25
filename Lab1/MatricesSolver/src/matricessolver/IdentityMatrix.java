package matricessolver;

import java.util.ArrayList;

public class IdentityMatrix extends Matrix
{
    private boolean IsIndetity()
    {
        for(int i = 0; i < _rows; i++){
            for(int j = 0; j < _cols; j++){
                int data = (i == j) ? 1 : 0;
                
                if(_data.get(i).get(j) != data) return false;
            }
        }
        
        return true;
    }
    
    private void ZeroOut()
    {
        for(int i = 0; i < _rows; i++){
            for(int j = 0; j < _cols; j++){
                _data.get(i).set(j, 0);
            }
        }
    }
    
    public IdentityMatrix(int n, int m) {
        super(n, m);
        
        if(n != m){
            System.out.print("Indetity Matrix has to be a square matrix");
        }
    }
    
    @Override
    public boolean SetData(ArrayList<Integer> data)
    {
        boolean res = super.SetData(data);
        
        if(res == false) return false;
        
        res = IsIndetity();
        
        if(res == false) {
            ZeroOut();
            System.out.print("Matrix is not an indentity\n");
        }
            
        return res;
    }
    
    @Override
    public IdentityMatrix Transpose()
    {
        return this;
    }
}
