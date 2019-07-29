/**
 * Audience
 */
public class Audience {

    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    public Long buy(Ticket ticket) {
        if (bag.hasInvitation()) {
            System.out.println("초대권이 있다.");            
            bag.setTicket(ticket);
            return 0L;
        } else {
            System.out.println("초대권이 없다.");
            bag.minusAmount(ticket.getFee());            
            bag.setTicket(ticket);
            return ticket.getFee();
        }
    }
}
