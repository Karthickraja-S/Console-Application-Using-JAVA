import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Train {
    public static int getCouchCapacity() {
        return couchCapacity;
    }

    public static char getSourcePoint() {
        return sourcePoint;
    }

    public static char getDestinationPoint() {
        return destinationPoint;
    }
    public static void addTicketList(Ticket ticket) {
        ticketList.add(ticket);
    }
    public static List<Ticket> getTicketList() {
        return ticketList;
    }
    public static int getMaxWaitingListCapacity() {
        return maxWaitingListCapacity;
    }
    public static char[][] getCoachDetails() {
        return coachDetails;
    }

    public static void reUpdateCoachDetails() {
        flushOld();
        for(Ticket ticketBooked : ticketList ) {
            char source      = ticketBooked.getSource();
            char destination = ticketBooked.getDestination();
            List<Integer> bookedSeats    = ticketBooked.getBookedSeats();
            for(char ch = source; ch < destination ; ch++) {
                int colIndex = ch - 'A' ;
                for(int seat : bookedSeats){
                    if(seat > 0) {
                        coachDetails[seat-1][colIndex] = '*';
                    }
                }
            }
        }
    }

    private static void flushOld() {
        coachDetails = new char[couchCapacity][destinationPoint - sourcePoint +1 ];
    }

    private static final int couchCapacity = 8;
    private static final int maxWaitingListCapacity = 2;
    private static final char sourcePoint = 'A';
    private static final char destinationPoint = 'E';
    private static char[][] coachDetails = new char[couchCapacity][destinationPoint - sourcePoint +1 ];

    private static final List<Ticket> ticketList = new ArrayList<>();
}
