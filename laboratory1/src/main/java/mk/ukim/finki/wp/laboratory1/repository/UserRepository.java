package mk.ukim.finki.wp.laboratory1.repository;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    List<User> Users;

    UserRepository () {
        Users = new ArrayList<>();
        Users.add(new User("antonio.stefanovski", "123", "Antonio", "Stefanovski"));
        Users.add(new User("antonio1.test", "ant", "Antonio", "Test"));
        Users.add(new User("stefanovski.antonio", "stant", "Antonio", "Stefanovski"));
    }

    public Optional<User> findByUsername(String username) {

        return Users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {

        return Users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

    }

    public User saveOrUpdate(User user) {

        Users.removeIf(u -> u.getUsername().equals(user.getUsername()));
        Users.add(user);

        return user;

    }

    public void deleteUser(User user) {

        Users.removeIf(u -> u.getUsername().equals(user.getUsername()));

    }

}
