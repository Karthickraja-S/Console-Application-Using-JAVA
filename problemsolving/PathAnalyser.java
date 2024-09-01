import java.util.ArrayList;
import java.util.List;

public class PathAnalyser {

    public static void main(String[] args) {
        int arr[][] = {{1, 1, 1},
                       {0, 1, 1},
                       {0, 1, 1}
        };

        printPathDetails(arr);
    }

    private static void printPathDetails(int[][] arr) {
        recurse(arr,0,0,2,2, "");
        System.out.println("--------------- SUMMARY ----------------");
        System.out.println("Mininum path : "+ getMinPath() );
        System.out.println("Maximum path : "+getMaxPath() );
        System.out.println("How many paths are there to reach destination : "+ pathsCovered.size() );
        System.out.println("What are the paths ? ");
        pathsCovered.stream().forEach(x -> System.out.println(x) );
    }

    private static int getMaxPath() {
        int maxVal = Integer.MIN_VALUE;
        for(String str : pathsCovered) {
            int covered = str.split("=>").length;
            if(covered > maxVal) {
                maxVal = covered;
            }
        }
        return maxVal;
    }

    private static int getMinPath() {
        int minVal = Integer.MAX_VALUE;
        for(String str : pathsCovered) {
            int covered = str.split("=>").length;
            if(covered < minVal) {
                minVal = covered;
            }
        }
        return minVal;
    }

    static List<String> pathsCovered = new ArrayList<>();
    private static void recurse(int[][] arr, int startR, int startC, int endR, int endC, String directions) {
        if(startR< 0 || startR >= arr.length || startC< 0 || startC>= arr[0].length || arr[startR][startC] == 0 ){
            return;
        }
        if(startR == endR && startC == endC) {
            directions += "END";
            pathsCovered.add(directions);
            return;
        }
        int tempToStoreAgain = arr[startR][startC];
        arr[startR][startC] = 0; // visited
        recurse(arr,startR-1,startC,endR,endC , directions + "TOP=>");
        recurse(arr,startR+1,startC,endR,endC,directions + "DOWN=>");
        recurse(arr,startR,startC+1,endR,endC,directions + "RIGHT=>");
        recurse(arr,startR,startC-1,endR,endC,directions + "LEFT=>");
        arr[startR][startC] = tempToStoreAgain;
    }
}
