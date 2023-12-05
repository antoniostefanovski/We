package mk.ukim.finki.wp.laboratory1.service.impl;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.ShoppingCart;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.model.User;
import mk.ukim.finki.wp.laboratory1.repository.TicketOrderRepository;
import mk.ukim.finki.wp.laboratory1.repository.UserRepository;
import mk.ukim.finki.wp.laboratory1.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;
    private final UserRepository userRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository, UserRepository userRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.userRepository = userRepository;
    }


    @Override
    public TicketOrder placeOrder(Movie movie, User user, Long numberOfTickets) {

        TicketOrder order = new TicketOrder(movie, user, numberOfTickets);

        return this.ticketOrderRepository.save(order);
    }
    @Override
    public List<TicketOrder> getTicketOrders() {
        return this.ticketOrderRepository.findAll();
    }

    @Override
    public TicketOrder findById(long id) {
        return ticketOrderRepository.findById(id);
    }

    @Override
    public void editTicketOrder(Movie movie, User user, Long numberOfTickets, Long ticketId) {

        Optional<TicketOrder> order = this.ticketOrderRepository.findById(ticketId);
        TicketOrder orderToSave = null;

        if(order != null) {
            orderToSave = order.get();
        }

        orderToSave.setMovie(movie);
        orderToSave.setUser(user);
        orderToSave.setNumberOfTickets(numberOfTickets);

        this.ticketOrderRepository.save(orderToSave);
    }

    @Override
    public List<TicketOrder> placeOrderFromShoppingCart(String username) {
        User user = userRepository.findByUsername(username);
        ShoppingCart cart = user.getCarts().get(0);

        ticketOrderRepository.saveAll(cart.getTicketOrders());

        return cart.getTicketOrders();
    }
}
