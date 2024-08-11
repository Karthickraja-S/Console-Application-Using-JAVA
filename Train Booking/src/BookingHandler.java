import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingHandler {
    /**
     * book,{source},{destination},{passenger_count}
     * @param input
     */
    public static void bookTickets(String input) {
        try {
        String[] parsedInput = input.split(",");
        if(parsedInput.length != 4) {
            throw new IllegalArgumentException("Input is invalid ! VALID INPUT : book,<source>,<destination>,<passenger_count>");
        }
            char source = parsedInput[1].charAt(0);
            char destination = parsedInput[2].charAt(0);
            int passengerCount = Integer.parseInt(parsedInput[3]);
            if (InputsValidator.checkSourceDestinationValid(source, destination)) {
                if( checkIfSeatsAvailable(source, destination, passengerCount) ) {
                    bookTickets(source, destination, passengerCount);
                } else {
                    SummaryPrinter.addSummary("No seats available");
                }
            } else {
                // custom exception would be more modular !
                throw new RuntimeException(" source/Destination is invalid ");
            }
        } catch (NumberFormatException e) {
            System.out.println("Passenger Count should be an integer ! ");
        } catch (RuntimeException e) {
            System.out.println( "Error" + e );
        } catch (Exception e) {
            System.out.println("Internal Server Error ! ");
        }
    }

    private static void bookTickets(char source, char destination, int passengerCount) {
        Ticket ticket = new Ticket();
        ticket.setSource(source);
        ticket.setDestination(destination);
        ticket.setPnrNo( Train.getTicketList().size() + 1 );
        // need to get booking seats according to already Booked List !
        List<Integer> bookingSeats = getSeatsToBeBooked(source,destination,passengerCount);
        ticket.setBookedSeats(bookingSeats);
        Train.addTicketList(ticket);

        SummaryPrinter.addSummary( ticket.getOutputFormatted() );
        System.out.println("********** Tickets booked ************");
        System.out.println(ticket.getOutputFormatted());
        System.out.println("**************************************");
        Train.reUpdateCoachDetails();
    }

    private static List<Integer> getSeatsToBeBooked(char source, char destination, int passengerCount) {
        List<Integer> goingToBookSeats = new ArrayList<>();
        Set<Integer> seatsBooked       = new HashSet<>();
        for(Ticket bookedTickets : Train.getTicketList()) {
            if(isTicketNeedToConsiderBookingForGivenSrcDest(bookedTickets,source,destination)) {
                // now his booked seat list can't be assigned to the current booking person !
                List<Integer> bookedSeat = bookedTickets.getBookedSeats();
                seatsBooked.addAll(bookedSeat);
            }
        }
        for(int i=1 ; i <= Train.getCouchCapacity() && passengerCount > 0 ; i++) {
            if(!seatsBooked.contains(i)) {
                goingToBookSeats.add(i);
                passengerCount--;
            }
        }

        int waitingList = seatsBooked.stream().anyMatch(x -> x<0) ? Collections.min(seatsBooked)-1 : -1;

        if(passengerCount > 0) {
            // still there are some passengers! need to allocate to WL only !
            // here we have to check if already WL has been allocated.
            // also here if already there is a one waiting list means, we have to give only one person to WL seats.
            // we don't have to consider about limit since we have already validated seats count before !
            while(passengerCount > 0) {
                goingToBookSeats.add(waitingList);
                waitingList -= 1;
                passengerCount--;
            }
        }
        System.out.println("DEBUG LOG : Going to Book seats :: "+goingToBookSeats);
        return goingToBookSeats;
    }

    /**
     * say im going to C->D
     * and now A->C OR D->E tickets can be omitted and only the ticket who travels within my travel path to be considered
     */
    private static boolean isTicketNeedToConsiderBookingForGivenSrcDest(Ticket ticket , char source , char destination) {
        if(source > ticket.getSource()) {
            // if he boards before me and ends before me
            // ex : he is going to A->C , im going to C->E
            if(source >= ticket.getDestination()) {
                return false;
            } else {
                // we have to consider his booked count.
                return true;
            }
        } else {
            // he is boarding after/with me , but my destination is before/at his boarding point
            // ex : he is going to C->E , im going to A->C
            if(ticket.getSource() >= destination) {
                return false;
            } else {
                return true;
            }
        }
    }
    /**
     * This will check how many ppl are travelling from source to destination.
     * @param source
     * @param destination
     * @return
     */
    private static int getBookedCount(char source , char destination) {
        // test : Also for this inputs book,A,E,2 ; book,A,C,3 ; book,C,E,2 ; book,C,E,3 ; book,B,E,1 ; book,A,E,2
        Set<Integer> seats = new HashSet<>();
        for(Ticket ticket : Train.getTicketList() ) {
           if(isTicketNeedToConsiderBookingForGivenSrcDest(ticket,source,destination)) {
               // this will include waiting list seats too !
               seats.addAll(ticket.getBookedSeats());
           }
        }
        return seats.size();
    }
    public static boolean checkIfSeatsAvailable(char source, char destination, int passengerCount) {
        // if source = C , dest = E ,  we have to check in booked tickets C->E booked count.
        // if booked count is 7 , and we are going to book for 3 person ( A-> E ) , 1 will be confirmed , 2 will be WL.
        int bookedPassengerCount = getBookedCount(source,destination);
        System.out.println("DEBUG LOG : Already booked passenger count for source "+source+" to destination "+destination+" is "+bookedPassengerCount);
        return (Train.getCouchCapacity() + Train.getMaxWaitingListCapacity() - bookedPassengerCount - passengerCount) >= 0;
    }

    /**
     * cancel,{pnr_no},{passenger_count_to_cancel}
     * @param input
     */
    public static void cancelTickets(String input) {
        try {
            String[] parsedInput = input.split(",");
            if (parsedInput.length != 3) {
                throw new RuntimeException("Input is invalid ! VALID INPUT : cancel,<pnr_no>,<passenger_count_to_cancel>");
            }
            int pnrNo = Integer.parseInt( parsedInput[1] );
            int passengerCount = Integer.parseInt( parsedInput[2] );
            Ticket ticket = InputsValidator.getTicketFindByPNR(pnrNo);
            if(ticket == null) {
                throw new IllegalArgumentException("Invalid PNR Number !!! ");
            }
            if(ticket.getBookedCount() < passengerCount) {
                throw new IllegalArgumentException("The given input count exceeds booked count !!! ");
            }
            // we have to cancel and allocate the cancelled tickets to the WL if any and update the seat layout !
            cancelTickets(ticket , passengerCount);
        }  catch (NumberFormatException e) {
            System.out.println("Passenger Count should be an integer ! ");
        } catch (RuntimeException e) {
            System.out.println( "Error" + e );
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Internal Server Error ! ");
        }
    }
    private static void cancelTickets(Ticket ticket, int passengerCountToCancel) {
        List<Integer> cancelledSeats = new ArrayList<>();
        while (passengerCountToCancel > 0) {
            int removedSeats = ticket.getBookedSeats().remove(0);
            System.out.println("DEBUG : removed seat : "+removedSeats + " , now booking list : "+ticket.getBookedSeats());
            cancelledSeats.add(removedSeats);
            passengerCountToCancel--;
        }

        List<Integer> alreadyCancelledSeats = ticket.getCancelledSeats();
        if(alreadyCancelledSeats != null) {
            alreadyCancelledSeats.addAll(cancelledSeats);
            ticket.setCancelledSeats(alreadyCancelledSeats);
        } else {
            ticket.setCancelledSeats(cancelledSeats);
        }

        SummaryPrinter.addSummary( ticket.getOutputFormatted() );
        System.out.println("********** Tickets cancelled ************");
        System.out.println(ticket.getOutputFormatted());
        System.out.println("**************************************");
        assignCancelledTicketsToWLIfAny(ticket);
        Train.reUpdateCoachDetails();
    }

    private static void assignCancelledTicketsToWLIfAny(Ticket cancelledTicket) {
        int index_ = 0;
        List<Integer> cancelledSeats = cancelledTicket.getCancelledSeats();
        for(Ticket ticket : Train.getTicketList()) {
            // why the below check ?
            // say we have full tickets booked for A->C (8) , C->E (8) , A->E ( 2 WL )
            // case 1 : if we cancel A->C ticket (8) then A->E waitingList tickets should not be confirmed since C->E is booked fully !
            // case 2 : if we have ticket from C->E (2) and if A->C (8) cancelled, then A->E can be confirmed !
            // case 3 : also if any one have WL of 3 ppl, then if 2 ppl can be confirmed means , 2 ppl need to confirm and 1 need to have WL
            List<Integer> bookedSeats = ticket.getBookedSeats();
            boolean updated = false;
            for (int index = 0; index < bookedSeats.size() && index_ < cancelledSeats.size() ; index++) {
                if (bookedSeats.get(index) < 0) {
                    int seat = getSeatsToBeBooked(ticket.getSource() , ticket.getDestination() , 1).get(0);
                    if(seat < 0) {
                        // still we get waiting list only , so we cant able to allocate seat ( like case 1 )
                        // so we've to ignore !
                        break;
                    }
                    System.out.println("Removed waiting list seat : " + bookedSeats.remove(index) + " to give seat" + seat + " for pnr : " + ticket.getPnrNo());
                    bookedSeats.add(index, seat);
                    System.out.println("Now for PNR " + ticket.getPnrNo() + " updated booking list will be " + bookedSeats);
                    index_++;
                    updated = true;
                }
            }
            if (updated) {
                SummaryPrinter.addSummary(ticket.getOutputFormatted());
                System.out.println("********** Tickets from Waiting list has been confirmed ************");
                System.out.println(ticket.getOutputFormatted());
                System.out.println("**************************************");
            }
        }
    }
}
