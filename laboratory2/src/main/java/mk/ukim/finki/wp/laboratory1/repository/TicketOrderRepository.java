package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.Movie;
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

    public TicketOrder placeOrder(Movie movie, String clientName, String address, Long numberOfTickets) {

        var ticketOrder = new TicketOrder(movie, clientName, address, numberOfTickets);

        TicketOrders.removeIf(t -> t.getClientName().equals(clientName));
        TicketOrders.add(ticketOrder);

        return ticketOrder;

    }

    public void editTicketOrder(Movie movie, String clientName, String address, Long numberOfTickets, Long ticketId) {

        TicketOrder ticketOrder = TicketOrders.stream().filter(t -> t.getTicketId().equals(ticketId)).findFirst().get();

        ticketOrder.setMovie(movie);
        ticketOrder.setClientName(clientName);
        ticketOrder.setNumberOfTickets(numberOfTickets);
        ticketOrder.setClientAddress(address);

    }

    public List<TicketOrder> getTicketOrders() {
        return TicketOrders;
    }

    public List<TicketOrder> getClientMovies(String clientName) {

        var tickets = getTicketOrders();

        return tickets.stream()
                .filter(u -> u.getClientName().equals(clientName)).toList();
    }

    public TicketOrder findById(long id) {
        return TicketOrders.stream()
                .filter(p -> p.getTicketId().equals(id)).findFirst().get();
    }

}
