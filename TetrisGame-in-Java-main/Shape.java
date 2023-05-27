public class Shape {

    // module 2 . can be extended easily to add many more shapes..
    static  char s[][]={{' ','*','*'},{'*','*',' '}};
    static char l[][] = {{'*',' '},{'*',' '},{'*','*'}};
    static char t[][] = {{'*','*','*'},{' ','*',' '},{' ','*',' '}};
    static char sq[][] = {{'*','*'},{'*','*'}};
    static char z[][]={{'*','*',' '},{' ','*','*'}};
    static char ml[][] = {{' ','*'},{' ','*'},{'*','*'}};
    static char i[][] = {{'*'},{'*'},{'*'}};

    static char[][] getShape(String ch)
    {
        switch(ch)
        {
            case "S":return s;
            case "L":return l;
            case "T":return t;
            case "SQ":return sq;
            case "Z":return z;
            case "ML":return ml;
            case "I":return i;
        }
        return null;
    }
}
