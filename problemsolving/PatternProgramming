import java.util.*;

public class PatternProgramming {
    /**
        input = 5
            1
          2 3 2
        3 4 5 4 3
      4 5 6 7 6 5 4
    5 6 7 8 9 8 7 6 5 4
         */
  public static void printPattern1(int R) {
        for(int row=1;row<=R ; row ++) {
            // here i need 8 space for 1st row , 6 for 2nd row , 4 for 3rd row , 2 for 4th row based on input
            for (int space = 0; space < ((R-row) * 2); space++) {  // why additional bracket ? BODMAS RULE !!
                System.out.print(" ");
            }
            int numToBePrinted = row;
            for(int printTimes=0;printTimes<row;printTimes++) {
                // here i want to print based on row;
                System.out.print(numToBePrinted++ + " ");
            }
            numToBePrinted-=2;
            for(int printTimes=row-1;printTimes>0;printTimes--) {
                // here i want to print based on row;
                System.out.print(numToBePrinted-- + " ");
            }
            System.out.println();
        }
    }
 public static void printSnakeMatrix(int[][] arr) {
    int row=0,col=0;
    boolean isUp = true;
    while(row!=arr.length-1 || col != arr[0].length-1) {
     // System.out.println("row : "+row+" col : "+col);
      System.out.print(arr[row][col]+"->");
      if(isUp) {
        // need to check whether top right directions..
        if(row-1 >= 0 && col+1 <= arr[0].length-1 ) {
          // we can move to top right.
          row--;
          col++;
        } else {
          // need to move right.
          // also if it reaches last column we have to move bottom..!
          if(col == arr[0].length-1) {
            row++;
          } else {
            col++;
          }
          isUp = false;
        }
      } else {
        // need to check bottom left directions..
        if(col-1 >= 0 && row+1 <= arr.length-1 ) {
          col--;
          row++;
        } else {
          // move to bottom.
          // also if it reaches last row, then we have to move right.
          if(row == arr.length-1) {
            col++;
          } else {
            row++;
          }
          isUp=true;
        }
      }
      
    }
   System.out.println(arr[row][col]); 
  }
     /**
     * {{1,2,3},<br>
     *  {4,5,6},<br>
     *  {7,8,9} <br>
     * }
     * OP -> 124753689
     * @param mat
     * PRINTING ZIGZAG MATRIX ...
     */
    public static void printDiagonalMatrix(int[][] mat) {
        int r=0,c=0;
        int R = mat.length,C=mat[0].length;
        boolean bottomLeft = false;
        while(r<R && c<C) {
            System.out.print(mat[r][c]+"->");
            if(!bottomLeft) {
                if (r > 0 && c < C-1) {
                    // top right is possible here
                    r--;
                    c++;
                } else {
                    // need to check whether need to go right OR bottom
                    if (c == C - 1) r++; // while going in top right , if i reach the corner then ,i need to come down
                    else c++;
                    bottomLeft = true;
                }
            } else {
                if (c>0 && r<R-1) {
                    // bottom left is possible here
                    r++;c--;
                } else {
                    // here also need to decide whether to go right OR bottom
                    if(r==R-1)c++;
                    else r++;
                    bottomLeft = false;
                }
            }
        }
    }
    /**
     *    1
     *   3 2
     *  6 5 4
     * 10 9 8 7
     * 10 9 8 7
     *  6 5 4
     *   3 2
     *    1
     * @param R
     */
    public static void printPattern2(int R) {
        int counter = 1;
        for(int row = 1; row<=R ; row++) {
            int numToBePrinted = counter;
            for(int space = R-row ; space>0; space--) {
                System.out.print(" ");
            }
            for(int numCount=1; numCount<=row; numCount++) {
                System.out.print(numToBePrinted-- + " ");
            }
            System.out.println();
            counter += (row+1);
        }
        // for R=4 , the counter will be 15 at this end. we have to revert again.
        counter -=  (R+1);
        for(int row=1; row<=R; row++) {
            for(int space = row ; space>1; space--) {
                System.out.print(" ");
            }
            for(int numCount=R; numCount>=row;numCount--) {
                System.out.print(counter-- + " ");
            }
            System.out.println();
        }
    }
  public static void main(String args[]){
      printPattern1(5);
      int arr[][] = {{1, 2, 3, 4},
                     {5, 6, 7, 8},
                     {9,10,11,12}
      };
      printSnakeMatrix(arr);
      printDiagonalMatrix(arr);
      printPattern2(10);
  }
}
