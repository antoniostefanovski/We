package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.User;

public interface AuthenticationService {

    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);


}
