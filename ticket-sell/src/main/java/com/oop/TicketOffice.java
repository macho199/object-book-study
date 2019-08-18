package com.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TicketOffice
 */
public class TicketOffice {

    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    /*    
    public void sellTicketTo(Audience audience) {
        // Audience에 의존되어 TicketOffice의 자율성이 낮아진다.
        plusAmount(audience.buy(getTicket()));
    }
    */

    /**
     * getTicket
     * @return Ticket
     */
    public Ticket getTicket() {
        return tickets.remove(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
