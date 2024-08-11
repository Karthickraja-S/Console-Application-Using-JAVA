public class InputsValidator {
    static boolean checkSourceDestinationValid(char source , char destination) {
        if(source == destination) {
            System.out.println("Source & destination should not be equal");
        } else if(source > destination ) {
            System.out.println("reverse is not possible");
        }
        else if(Train.getSourcePoint() <= source &&
                Train.getDestinationPoint() >= source &&
                Train.getDestinationPoint() >= destination) {
            return true;
        }
        return false;
    }
    static Ticket getTicketFindByPNR(int pnrNo) {
        for(Ticket ticket : Train.getTicketList())
        {
            if(ticket.getPnrNo() == pnrNo) {
                return ticket;
            }
        }
        return null;
    }
}
