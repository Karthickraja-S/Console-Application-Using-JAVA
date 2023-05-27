import java.util.Arrays;

public class Function {
    
    static void printMatrix(char mat[][])
    {
        for(int row=0;row<mat.length;row++)
        {
            for(int col=0;col<mat[0].length;col++)
            {
                System.out.print(mat[row][col]+" ");
            }
            System.out.println();
        }
    }
    static void initialFill(char mat[][])
    {
        for(int row=0;row<mat.length;row++)
        {
            for(int col=0;col<mat[0].length;col++)
            {
                if(row==0 || row==mat.length-1 || col==0 || col==mat[0].length-1)
                {
                    mat[row][col] = '*';
                }
                else
                {
                    mat[row][col]=' ';
                }
            }
        }
    }
    public static void spawn(char[][] shapeMatrix, char[][] matrix,int startRowPos,int startColPos) {
        //fillUp shape in matrix
        for(int row=0;row<shapeMatrix.length;row++)
        {
            for(int col=0;col<shapeMatrix[0].length;col++)
            {
                if(shapeMatrix[row][col]!=' ') {
                // if this condition is not given , then it also replaces 
                // space in the playableGrid . check spawnConditionReason picture
                matrix[startRowPos+row][startColPos+col] = shapeMatrix[row][col];
                }
            }
        }
    }

    public static void reset(char[][] shapeMatrix, char[][] matrix,int startRowPos,int startColPos) {
        //fillUp shape in matrix
        for(int row=0;row<shapeMatrix.length;row++)
        {
            for(int col=0;col<shapeMatrix[0].length;col++)
            {
                if(shapeMatrix[row][col]!=' ')
                {
                    matrix[startRowPos+row][startColPos+col] = ' ';
                }
            }
        }
    }
    public static int calculateScore(char[][] playableGrid, int score) {
        int sc=0;
        // check for rows filled to increase score and eliminate the row
        // fill all the above *s to the below...
        for(int row=playableGrid.length-2;row>1;row--)
        {
            // checking from bottom..
            boolean toBeRemoved = true;
            for(int col=1;col<playableGrid[0].length-2;col++)
            {
                if(playableGrid[row][col]!='*')
                {
                    toBeRemoved = false;
                    break;
                }
            }
            // if all the column has * then , the score need to updated and 
            // the row has to eliminate and all the above element need to brought below
            if(toBeRemoved)
            {
                sc=100;
                for(int selectedRow = row;selectedRow>1;selectedRow--)
                {
                    for(int col=1;col<playableGrid[0].length-2;col++)
                    {
                        // replaces the above value to below
                        playableGrid[selectedRow][col] = playableGrid[selectedRow-1][col];
                    }
                }
            }
        }
        return sc;
    }
}
