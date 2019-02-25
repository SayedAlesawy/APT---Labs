package matricessolver;

import java.util.List;
import java.util.ArrayList;

public class Matrix implements Addable
{
    /*Data members for the Matrix class*/
    protected List<List<Integer>> _data;
    protected int _rows;
    protected int _cols;
    
    /*A function to intialize a matrix*/
    private void InitializeMatrix(List<List<Integer>> data, int n, int m)
    {
        for(int i = 0; i < n; i++){
            data.add(new ArrayList<>());
            
            for(int j = 0; j < m; j++){
                data.get(i).add(0);
            }
        }
    }
    
    /*A function to swap rows and cols in case of trasnpose*/
    private void SwapDimensions()
    {
        int tmp = _rows;
        _rows = _cols;
        _cols = tmp;
    }
    
    /*The Matrix constructor*/
    public Matrix(int n, int m)
    {
        _rows = n;
        _cols = m;
        
        _data = new ArrayList<>();
        InitializeMatrix(this._data, this._rows, this._cols);
    }
    
    /*A getter function to get the rows*/
    public int GetRows()
    {
        return this._rows;
    }
    
    /*A getter function to get the cols*/
    public int GetCols()
    {
        return this._cols;
    }
    
    /*A getter function to ge the data*/
    public List<List<Integer>> GetData()
    {
        return this._data;
    }
    
    /*A function to set data of a matrix*/
    boolean SetData(ArrayList<Integer> data)
    {
        if(data.size() != _rows * _cols){
            System.out.printf("Not enough values to fill a %d x %d matrix\n", _rows, _cols);
            return false;
        }
        
        for(int i = 0, j = 0, k = 0; i < data.size(); i++, k++){
            if(i > 0 && i % _cols == 0) {
                j++;
                k = 0;
            }
            
            _data.get(j).set(k, data.get(i));
        }
        
        return true;
    }
    
    /*A function to print a Matrix*/
    void Print()
    {
        System.out.print("Matrix = {\n");
        
        for(int i = 0; i < _rows; i++){
            System.out.print("  ");
            
            for(int j = 0; j < _cols; j++){
                System.out.printf("%d", _data.get(i).get(j));
                if(j + 1 < _cols) System.out.print(" ");
            }
            System.out.print("\n");
        }
        
        System.out.print("}\n");
    }
    
    /*A function that override the add function in the Addable interface to add 2 matrices*/
    @Override
    public Matrix Add(Matrix mat) 
    {
        if((mat.GetRows() != this._rows) || (mat.GetCols() != this._cols)){
            System.out.print("[Apport Addition] Matrix dimensions mismatch\n");
            return this;
        }
        
        Matrix newMat = new Matrix(_rows, _cols);
        ArrayList<Integer> sumResult = new ArrayList<>();
        
        for(int i = 0; i < _rows; i++){
            for(int j = 0; j < _cols; j++){
                int mat1 = this._data.get(i).get(j);
                int mat2 = mat.GetData().get(i).get(j);
          
                sumResult.add(mat1 + mat2);
            }
        }
        
        newMat.SetData(sumResult);
        
        return newMat;
    }
    
    /*A function that gets the transpose of a matrix*/
    public Matrix Transpose()
    {
        List<List<Integer>> transposeData = new ArrayList<>();
        InitializeMatrix(transposeData, this._cols, this._rows);
        
        for(int i = 0; i < _rows; i++){
            for(int j = 0; j < _cols; j++){
                transposeData.get(j).set(i, this._data.get(i).get(j));
            }
        }
        
        this._data = transposeData;
        SwapDimensions();
        
        return this;
    }
}
