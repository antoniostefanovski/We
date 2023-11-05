package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.User;
import mk.ukim.finki.wp.laboratory1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.laboratory1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.laboratory1.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.laboratory1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {

        if (username==null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);

    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {

        if (username==null || username.isEmpty()  || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        User user = new User(username,password,name,surname);
        return userRepository.saveOrUpdate(user);

    }
}
