package mk.ukim.finki.wp.laboratory1.model;

import lombok.Data;

@Data
public class TicketOrder {
    private Long ticketId;
    private Movie movie;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;

    public TicketOrder(Movie movie, String clientName, String clientAddress, Long numberOfTickets) {
        this.ticketId = (long) (Math.random() * 1000);
        this.movie = movie;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
