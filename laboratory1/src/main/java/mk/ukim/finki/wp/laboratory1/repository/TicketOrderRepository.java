package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOrderRepository {

    List<TicketOrder> TicketOrders;

    public TicketOrderRepository() {
        TicketOrders = new ArrayList<>();
    }

    public List<TicketOrder> getTicketOrders() {
        return TicketOrders;
    }

    public TicketOrder placeOrder(String movieTitle, String clientName, String address, Long numberOfTickets) {

        var ticketOrder = new TicketOrder(movieTitle, clientName, address, numberOfTickets);

        TicketOrders.add(ticketOrder);

        return ticketOrder;

    }

    public List<String> getClientMovies(String clientName) {

        var tickets = getTicketOrders();

        return tickets.stream()
                .filter(u -> u.getClientName().equals(clientName))
                .map(TicketOrder::getMovieTitle)
                .toList();

    }



}
