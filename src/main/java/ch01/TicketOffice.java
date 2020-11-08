package ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }

    public void minusAmonut(Long amount) {
        this.amount -= amount;
    }

    public void plusAmonut(Long amount) {
        this.amount += amount;
    }
}