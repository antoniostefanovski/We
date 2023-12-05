package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    User findUserByUsername(String username);

}
