package mk.ukim.finki.wp.laboratory1.service.impl;

import mk.ukim.finki.wp.laboratory1.model.User;
import mk.ukim.finki.wp.laboratory1.repository.UserRepository;
import mk.ukim.finki.wp.laboratory1.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
