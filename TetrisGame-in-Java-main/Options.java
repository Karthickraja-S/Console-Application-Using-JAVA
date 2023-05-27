public class Options {
    // module 3 and 4
    static char[][] rotateLeft(char mat[][])
    {
        // just take last column make it as first row and so on..
        char rotated[][] = new char[mat[0].length][mat.length];
        for(int col=mat[0].length-1;col>=0;col--)
        {   // 1 
            for(int row=0;row<mat.length;row++)
            {
                // 0 1 2
                // 0 1 , 1 1 , 2 1 , 0 0 , 1 0 , 2 0 ==> 0 0 , 0 1 , 0 2 , 1 0 , 1 1 , 1 2
                rotated[mat[0].length-col-1][row] = mat[row][col];
            }
        }
       return rotated;
    }
    static char[][] rotateRight(char mat[][])
    {
        // just take last row make it as first column as straight and so on..
        char 
        rotated[][] = new char[mat[0].length][mat.length];
        for(int row=mat.length-1;row>=0;row--)
        { 
            for(int col=0;col<mat[0].length;col++)
            {
                rotated[col][mat.length-row-1] = mat[row][col];
            }
        }
        return rotated;
    }
    public static void moveLeft(char[][] shape, char[][] playableGrid, int startRowPos, int startColPos) 
    {
       Function.spawn(shape, playableGrid, startRowPos, startColPos);
    }
    public static void moveDown(char[][] shape, char[][] playableGrid, int startRowPos, int startColPos) 
    {
        Function.spawn(shape, playableGrid, startRowPos, startColPos);
    }
    public static void moveRight(char[][] shape, char[][] playableGrid, int startRowPos, int startColPos) 
    {
        Function.spawn(shape, playableGrid, startRowPos, startColPos);
    }
    public static void fix(char[][] shape, char[][] playableGrid, int startRowPos, int startColPos) {
        Function.spawn(shape, playableGrid, startRowPos, startColPos);
    }
    
}
