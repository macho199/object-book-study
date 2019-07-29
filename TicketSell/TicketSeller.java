/**
 * TicketSeller
 */
public class TicketSeller {

    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        // TicketSeller의 자율성은 높일 수 있지만!
        // ticketOffice.sellTicketTo(audience);

        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
    }
}
