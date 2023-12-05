package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketOrderService {

    TicketOrder placeOrder(Movie movie, User user, Long numberOfTickets);

    List<TicketOrder> getTicketOrders();

    TicketOrder findById(long id);

    void editTicketOrder(Movie movie, User user, Long numberOfTickets, Long ticketId);

    List<TicketOrder> placeOrderFromShoppingCart(String username);
}
