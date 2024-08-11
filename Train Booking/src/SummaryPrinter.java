import java.util.ArrayList;
import java.util.List;

public class SummaryPrinter {
    private static final List<String> summaryData = new ArrayList<>();
    public static void printSummary() {
        System.out.println("******** Summary ***********");
        for(String data : summaryData) {
            System.out.println(data);
        }
        System.out.println("********* Chart **********");

        char[][] matrix = Train.getCoachDetails();

        System.out.print("  ");
        for(char ch = Train.getSourcePoint(); ch <= Train.getDestinationPoint() ; ch++) {
            // A B C D E ...
            System.out.print(ch+" ");
        }
        System.out.println();

        for(int row = 0; row < matrix.length; row++ ) {
            System.out.print(row+1+" ");  // first column
            for(int col=0; col < matrix[0].length; col++ ) {
                char ch = matrix[row][col];
                System.out.print(ch != 0 ? ch : " " );
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void addSummary(String input) {
        summaryData.add(input);
    }
}
