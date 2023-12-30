import java.util.*;

public class Main
{
    static boolean isClockwise(char[] ch , char curr , char next) {
        for(int i=0;i<ch.length;i++){
            if(ch[i] == curr){
                if(i == ch.length-1 && ch[0]==next) {
                    // will be clockwise 
                    return true;
                } else if(ch[i+1]==next) {
                    // clockwise
                    return true;
                } 
            }
        }
        return false;
    }
    static int getPosOfPlayer(char ch , char[] seat) {
        for(int i=0;i<seat.length;i++){
            if(seat[i]==ch){
                return i;
            }
        }
        return -1;
    }
    static char[] findCurrPlayerAndNextPlayer(boolean isClockwise , char[] seat , int count , char currPlayer) {
        char[] ans = new char[2];
        int currPos = getPosOfPlayer(currPlayer , seat);
        int len = seat.length;
        if(isClockwise) {
            // if the ball need to pass 7 times , and the length of seat is 4 , if last person is passing ball
            // means after4 times he has the ball again , after that only we need to calculate.
            int needToPass = count%len;
            while(needToPass-- >0){
                currPos++;
                currPos = currPos%len;
            }
            ans[0] = seat[currPos];
            ans[1] = seat[(currPos+1)%len];
        } else {
             int needToPass = count%len;
            while(needToPass-- >0){
                currPos--;
                if(currPos<0){
                    currPos = len-1;
                }
            }
            
            ans[0] = seat[currPos];
            if(currPos-1 < 0){
             ans[1] = seat[len-1];
            } else {
                ans[1] = seat[currPos-1];
            }
            
        }
        return ans;
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
		Scanner ip = new Scanner(System.in);
		int numberOfFrnds = ip.nextInt();
		char ch[] = new char[numberOfFrnds];
		
		for(int i=0;i<numberOfFrnds;i++) {
		    ch[i] = ip.next().charAt(0); // followed by name of friends.willl be unique
		}
		int noOfTimesBallPassed = ip.nextInt();
		
		char currentPlayer = ip.next().charAt(0); // initially ball will be to him. 
		char nextPlayer = ip.next().charAt(0);  // the ball is passed to him. so noOfTimesBallPassed-1 , then we need to calculate whom has 
		// curr player and nextPlayer and direction.
		boolean isClockwise = isClockwise(ch , currentPlayer , nextPlayer);
		System.out.println(isClockwise? "Clockwise":"Anti-Clockwise");
		
		char[] ans = findCurrPlayerAndNextPlayer(isClockwise , ch , noOfTimesBallPassed-1 , nextPlayer);
		System.out.println("CurrentPlayer => "+ans[0]);
		System.out.println("NextPlayer => "+ans[1]);
	}
}
