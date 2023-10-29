import java.util.*;


class BallGame
{
    static char[][] mat = null;
    static Scanner ip =  new Scanner(System.in);
    private static void printMatrix() {
        for(int row=0;row<mat.length;row++){
            for(int col=0;col<mat[0].length;col++){
                System.out.print(mat[row][col]+" ");
            }
            System.out.println();
        }
    }
    public static void intialFill(int R , int C , int ballPos) {
        mat = new char[R][C];
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(row==0||row==R-1||col==0||col==C-1){
                    mat[row][col]= '*';
                } else if(col==ballPos && row==R-2){
                    mat[row][col]='B';
                } else {
                    mat[row][col]='-';
                }
            }
        }
    }
    public static void swap(int row,int col,int nrow , int ncol){
        char temp = mat[row][col];
        mat[row][col] = mat[nrow][ncol];
        mat[nrow][ncol] = temp;
    }

    public static int moveBallLeftOrRight(char ch , int row , int col) throws Exception {
        boolean left = true;
        boolean up = true;
        if(ch == 'R'){
            left = false;
        }
        do {
            if (col == 1) left = false;
            if (col == mat[0].length-2) left = true;  // if this condition meets , then the ball cant able to move right further.
            if (row - 1 == 0) up = false;
            if(left) {
                if(up){
                    swap(row,col,--row,--col); //  top left moving
                } else {
                    swap(row,col,++row,--col); // down left moving
                }
            } else {
                if(up){
                    swap(row,col,--row,++col); //  top right moving
                } else{
                    swap(row,col,++row,++col); // down right moving
                }
            }
            Thread.sleep(1000);
//                System.out.println("--------------------------------------");
//                System.out.println("row pos : "+row+" col pos : "+col);
//                System.out.println("--------------------------------------");
            printMatrix();
        }while (row!=mat.length-2);   // row != mat.length-2 is the condition that ball has reached ground !!
        return col;
    }
    public static int moveBall(char ch,int row,int col) throws Exception {
        if(ch == 'U'){
            boolean up = true;
            do{
                if(up) {
                    swap(row, col, --row, col);
                } else {swap(row, col, ++row, col);}

                if(row-1==0){
                    up = false;
                }
                printMatrix();
            }while (row != mat.length-2);
        }
        return moveBallLeftOrRight(ch,row,col);
    }
    public static void main(String[] args) throws Exception
    {

        System.out.println("Enter row size : ");
        int R = ip.nextInt();
        System.out.println("Enter column size : ");
        int C = ip.nextInt();
        System.out.println("Enter ball position at bottom : ( 1 to Column.size-1 ) ");
        int pos = ip.nextInt();
        if(pos < 1 || pos > C-1){
            System.out.println("The ball position needs to be within column size defined. ( 1 to C-1 )");
            return;
        }
        intialFill(R,C,pos);
        printMatrix();
        System.out.println("Ball needs to move in which direction ? L-left ; R-Right ; U-Up");
        char ch = ip.next().charAt(0);
        switch (ch) {
            case 'L':
            case 'U':
            case 'R':
                int finalPos = moveBall(ch,R-2,pos);
                System.out.println("BALL WILL BE IN FINAL POSITION => "+finalPos);
                break;
            default:
                System.out.println("Invalid choice !!");
        }
    }


}
