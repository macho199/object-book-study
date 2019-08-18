package com.oop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TicketOffice ticketOffice = new TicketOffice(Long.valueOf(100), new Ticket(), new Ticket());
        TicketSeller ticketSeller = new TicketSeller(ticketOffice);
        Theater theater = new Theater(ticketSeller);

        Bag bag = new Bag(new Invitation(), Long.valueOf(10));
        Audience audience = new Audience(bag);
        theater.enter(audience);
    }
}
