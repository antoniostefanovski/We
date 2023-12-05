package mk.ukim.finki.wp.laboratory1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private double rating;
    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;
    @OneToOne
    private TicketOrder ticketOrder;

    public Movie(String title, String summary, double rating, Production production) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }

    public Movie() {}
}
