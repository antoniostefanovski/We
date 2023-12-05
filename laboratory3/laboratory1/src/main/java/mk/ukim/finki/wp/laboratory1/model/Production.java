package mk.ukim.finki.wp.laboratory1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "production")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "production_name")
    private String name;
    private String country;
    private String address;
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    private List<Movie> movies;

    public Production(String name, String country, String address) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Production() {}
}
