import java.util.*;
public class PlayTheGame {
    char[][] shape = null;
    char[][] playableGrid = null;
    int startRowPos;
    int startColPos;

    PlayTheGame(char[][] shape , char[][] playableGrid)
    {
        this.shape = shape;
        this.startRowPos=1;
        this.startColPos = playableGrid[0].length/2-1;
        this.playableGrid = playableGrid;
    }

    public boolean isGameEnd()
    {
        // if the incoming shape is not able to fit in the playable grid means the game got end.
        for(int row=0;row<shape.length;row++)
        {
            for(int col=0;col<shape[0].length;col++)
            {
                if(shape[row][col]=='*' && playableGrid[startRowPos+row][startColPos+col]=='*')
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void start() {  

        Function.spawn(shape,playableGrid,startRowPos,startColPos); // module 2
        Function.printMatrix(playableGrid);
        Scanner ip = new Scanner(System.in);

        while(isValidDownMove())  // runs until the shape hits the bottom floor
        {
            System.out.println("E-rotate right; Q-rotate Left; A-Move Left; S-Move Down; D-Move Right");
            String option = ip.nextLine();
            switch(option)
            { 
                case "A":
                        if(!(startColPos-1>0)) {
                            System.out.println("cant able to move left further. ");
                            break;
                        }
                        // reseting the shape to ' ' and after moving the matrix
                        Function.reset(shape,playableGrid,startRowPos,startColPos);
                        startColPos--;
                        Options.moveLeft(shape,playableGrid,startRowPos,startColPos);
                        break;
                case "S":
                        Function.reset(shape,playableGrid,startRowPos,startColPos);
                        startRowPos++;
                        Options.moveDown(shape,playableGrid,startRowPos,startColPos);
                        // after moving down the isValidDownMove handles this case.
                        break;
                case "D":
                        if(startColPos+shape[0].length+1 >= playableGrid[0].length) {
                            System.out.println("cant able to move right further. ");
                            break;
                        }
                        Function.reset(shape,playableGrid,startRowPos,startColPos);
                        startColPos++;
                        Options.moveRight(shape,playableGrid,startRowPos,startColPos);
                        break;
                case "E":
                        Function.reset(shape,playableGrid,startRowPos,startColPos);
                        shape = Options.rotateRight(shape);
                        Options.fix(shape,playableGrid,startRowPos,startColPos);
                        break;
                case "Q":
                        Function.reset(shape,playableGrid,startRowPos,startColPos);
                        shape = Options.rotateLeft(shape);
                        Options.fix(shape,playableGrid,startRowPos,startColPos);
                        break;
            }
            Function.printMatrix(playableGrid);
        }
    }

    private boolean isValidDownMove() {
        int shapeRowLen = shape.length-1;
        for(int col=0;col<shape[0].length;col++)
        {
            if(shape[shapeRowLen][col]=='*' && playableGrid[startRowPos+shapeRowLen+1][col+startColPos]=='*')
            {
                // thus reached a boundary
            //    System.out.println(shapeRowLen+" "+(startRowPos+shapeRowLen)+" "+col+startColPos+" "+startRowPos);
                return false;
            }
        }
        return true;
    }
}
