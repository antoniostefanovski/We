package mk.ukim.finki.wp.laboratory1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket_order")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private Long numberOfTickets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public TicketOrder(Movie movie, User user, long numberOfTickets) {
        this.movie = movie;
        this.user = user;
        this.numberOfTickets = numberOfTickets;
    }

    public TicketOrder() {}
}
