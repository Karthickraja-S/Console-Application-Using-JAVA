import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("------ Welcome ------");
        Scanner ip = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the input " +
                    "\n book,<source>,<destination>,<passenger_count>"+
                    "\n cancel,<pnr_no>,<passenger_count>"+
                    "\n chart"+
                    "\n exit");
            System.out.println("---------------------------------");
            String input = ip.nextLine();
            String option = input.split(",")[0];
            switch (option) {
                case "book":
                    /* edge case inputs
                    INPUT : A,E,1 ; C,E,3 ; A,C,2 ; A,D,2book ; A,B,1 ; A,E,3 ; A,E,1 ; D,E,2 ; D,E,3 ; D,E,2
                    PNR 1, A to E , Seat Nos : 1
                    PNR 2, C to E , Seat Nos : 2,3,4
                    PNR 3, A to C , Seat Nos : 2,3
                    PNR 4, A to D , Seat Nos : 5,6
                    PNR 5, A to B , Seat Nos : 4
                    PNR 6, A to E , Seat Nos : 7,8,WL1
                    PNR 7, A to E , Seat Nos : WL2
                    PNR 8, D to E , Seat Nos : 5,6 ( VERIFY EACH WITH CHART !! )
                    No Seats available
                    */
                    BookingHandler.bookTickets(input);
                    break;
                case "cancel":
                    BookingHandler.cancelTickets(input);
                    break;
                case "chart":
                    SummaryPrinter.printSummary();
                    break;
                case "exit":
                    System.out.println("Exiting ! ");
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
}
