package matricessolver;

import java.util.ArrayList;
import java.util.Arrays;

public class MatricesSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // A test scenario
        
        //Matrix dimensions variables
        int rows = 3;
        int cols = 2;
        
        //Instantiating a matrix of size rows x cols
        Matrix mat = new Matrix(rows, cols);
        
        //Test instantiation --> A zero matrix of size rows x cols
        mat.Print();
        
        //Test setting numbers --> Fill the matrix with values
        //Test 1 (Invalid input)
        ArrayList<Integer> invalidData = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4)
        );
        
        boolean res = false;
        
        res = mat.SetData(invalidData);
        if(res == false) System.out.print("Test Failed\n");
        else System.out.print("Test Succeeded\n");
        mat.Print();
        
        //Test 2 --> Valid data
        ArrayList<Integer> validData = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        
        res = mat.SetData(validData);
        if(res == false) System.out.print("Test Failed\n");
        else System.out.print("Test Succeeded\n");
        mat.Print();
        
        //Testing the add function
        //Test 1 --> invalid addition
        Matrix toBeAddedInvalid = new Matrix(2, 2);
        ArrayList<Integer> toBeAddedInvalidData = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4)
        );
        toBeAddedInvalid.SetData(toBeAddedInvalidData);
        toBeAddedInvalid.Print();
        
        Matrix sumResultInvalid = mat.Add(toBeAddedInvalid);
        sumResultInvalid.Print();
        
        //Test 2 --> valid input
        Matrix toBeAddedvalid = new Matrix(3, 2);
        ArrayList<Integer> toBeAddedValidData = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        toBeAddedvalid.SetData(toBeAddedValidData);
        toBeAddedvalid.Print();
        
        Matrix sumResultValid = mat.Add(toBeAddedvalid);
        sumResultValid.Print();
        
        //Testing the transpose function
        mat.Transpose();
        System.out.printf("Rows = %d, Cols = %d\n", mat.GetRows(), mat.GetCols());
        mat.Print();
        
        mat.Transpose();
        System.out.printf("Rows = %d, Cols = %d\n", mat.GetRows(), mat.GetCols());
        mat.Print();
        
        //Testing indentity Matrix
        IdentityMatrix Imat = new IdentityMatrix(3, 3);
        //Imat.Print();
        
        //Setting an indentity Matrix
        //Test 1 --> InvalidData 
        ArrayList<Integer> indentityInvalidData = new ArrayList<>(
                    Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        Imat.SetData(indentityInvalidData);
        Imat.Print();
        
        //Test 2 --> ValidData
        ArrayList<Integer> indentityValidData = new ArrayList<>(
                    Arrays.asList(1, 0, 0, 0, 1, 0, 0, 0, 1)
        );
        Imat.SetData(indentityValidData);
        Imat.Print();
        
        //Testing transpose on Indentity
        Imat.Transpose();
        Imat.Print();
    }
    
}
