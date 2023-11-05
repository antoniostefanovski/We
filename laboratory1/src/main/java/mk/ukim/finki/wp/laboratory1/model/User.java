package mk.ukim.finki.wp.laboratory1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    String username;
    String password;
    String name;
    String surname;

}
