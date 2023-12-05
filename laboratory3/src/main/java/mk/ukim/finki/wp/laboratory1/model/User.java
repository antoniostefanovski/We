package mk.ukim.finki.wp.laboratory1.model;


import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp.laboratory1.model.converters.UserFullName;
import mk.ukim.finki.wp.laboratory1.model.converters.UserFullNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullName;

    @Column(name = "client_password")
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShoppingCart> carts;
}
