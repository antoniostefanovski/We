package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ShoppingCartService {
    void addToCart(String movieTitle, String clientName, int numberOfTickets);

    ShoppingCart getUserShoppingCart(String username);

    List<ShoppingCart> getAllShoppingCartsInDate(LocalDateTime from, LocalDateTime to);
}
