package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketOrderService {

    TicketOrder placeOrder(Movie movie, String clientName, String address, Long numberOfTickets);

    List<TicketOrder> getClientMovies(String clientName);

    List<TicketOrder> getTicketOrders();

    TicketOrder findById(long id);

    public void editTicketOrder(Movie movie, String clientName, String address, Long numberOfTickets, Long ticketId);

}
