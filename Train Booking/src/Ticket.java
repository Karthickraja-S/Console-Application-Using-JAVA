import java.util.Iterator;
import java.util.List;

public class Ticket {
    public int getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(int pnrNo) {
        this.pnrNo = pnrNo;
    }

    public char getSource() {
        return source;
    }

    public void setSource(char source) {
        this.source = source;
    }

    public char getDestination() {
        return destination;
    }

    public void setDestination(char destination) {
        this.destination = destination;
    }

    public int getBookedCount() {
        return bookedSeats.size();
    }
    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public List<Integer> getCancelledSeats() {
        return cancelledSeats;
    }

    public void setCancelledSeats(List<Integer> cancelledSeats) {
        this.cancelledSeats = cancelledSeats;
    }

    public String getOutputFormatted() {
        String cancelledSeatStr = getFormattedSeats(cancelledSeats);
        String returnSTR =  "PNR "+ pnrNo + ", "+source + " to " + destination + " , Seat Nos : " + getFormattedSeats(bookedSeats);
        if(!cancelledSeatStr.equalsIgnoreCase("")) {
            returnSTR = returnSTR + " Cancelled Seats : "+cancelledSeatStr;
        }
        return returnSTR;
    }

    private String getFormattedSeats(List<Integer> seats) {
        StringBuilder seatBuilder = new StringBuilder();
        if(seats != null) {
            Iterator<Integer> it = seats.iterator();
            while (it.hasNext()) {
                int num = it.next();
                if (num > 0) {
                    seatBuilder.append(num);
                } else {
                    seatBuilder.append("WL").append(num * -1);
                }
                if (it.hasNext()) {
                    seatBuilder.append(",");
                }
            }
        }
        return seatBuilder.toString();
    }
    private int pnrNo;
    private char source;
    private char destination;
    private List<Integer> bookedSeats; // here if any integer is found to be negative, then it is WAITING LIST SEATS
    private List<Integer> cancelledSeats;
}
