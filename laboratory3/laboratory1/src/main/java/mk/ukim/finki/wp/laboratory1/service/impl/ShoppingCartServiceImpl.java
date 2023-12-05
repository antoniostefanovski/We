package mk.ukim.finki.wp.laboratory1.service.impl;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.ShoppingCart;
import mk.ukim.finki.wp.laboratory1.model.TicketOrder;
import mk.ukim.finki.wp.laboratory1.model.User;
import mk.ukim.finki.wp.laboratory1.repository.MovieRepository;
import mk.ukim.finki.wp.laboratory1.repository.ShoppingCartRepository;
import mk.ukim.finki.wp.laboratory1.repository.TicketOrderRepository;
import mk.ukim.finki.wp.laboratory1.repository.UserRepository;
import mk.ukim.finki.wp.laboratory1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    private final TicketOrderRepository ticketOrderRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, MovieRepository movieRepository, TicketOrderRepository ticketOrderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public void addToCart(String movieTitle, String clientName, int numberOfTickets) {
        User user = userRepository.findByUsername(clientName);
        Movie movie = movieRepository.findById(Long.parseLong(movieTitle));

        if (user != null) {
            List<ShoppingCart> carts = user.getCarts();

            ShoppingCart shoppingCart;
            if (carts != null && !carts.isEmpty()) {
                shoppingCart = carts.get(0);
            } else {
                shoppingCart = new ShoppingCart();
                shoppingCart.setUser(user);
                shoppingCart.setDateCreated(LocalDateTime.now());

                carts = new ArrayList<>();
                carts.add(shoppingCart);
                user.setCarts(carts);
            }

            TicketOrder newTicketOrder = new TicketOrder(movie, user, numberOfTickets);

            newTicketOrder.setShoppingCart(shoppingCart);
            newTicketOrder.setUser(user);

            List<TicketOrder> ticketOrders = shoppingCart.getTicketOrders();
            if (ticketOrders == null || ticketOrders.isEmpty()) {
                ticketOrders = new ArrayList<>();
            }

            ticketOrders.add(newTicketOrder);

            shoppingCart.setTicketOrders(ticketOrders);

            userRepository.save(user);
        }
    }


    @Override
    public ShoppingCart getUserShoppingCart(String username) {
        User user = userRepository.findByUsername(username);

        List<ShoppingCart> carts = user.getCarts();

        return carts.get(0);
    }

    @Override
    public List<ShoppingCart> getAllShoppingCartsInDate(LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            return shoppingCartRepository.findByDateCreatedBetween(from, to);
        } else if (from != null) {
            return shoppingCartRepository.findByDateCreatedAfter(from);
        } else if (to != null) {
            return shoppingCartRepository.findByDateCreatedBefore(to);
        } else {
            return shoppingCartRepository.findAll();
        }
    }
}
