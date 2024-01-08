package mk.ukim.finki.wp.kol2022.g3.service.impl;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidForumUserIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.ForumUserRepository;
import mk.ukim.finki.wp.kol2022.g3.repository.InterestRepository;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ForumUserServiceImpl implements ForumUserService, UserDetailsService {

    private final ForumUserRepository forumUserRepository;
    private final InterestService interestService;
    private final PasswordEncoder passwordEncoder;

    public ForumUserServiceImpl(ForumUserRepository forumUserRepository, InterestService interestService, PasswordEncoder passwordEncoder) {
        this.forumUserRepository = forumUserRepository;
        this.interestService = interestService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ForumUser> listAll() {
        return this.forumUserRepository.findAll();
    }

    @Override
    public ForumUser findById(Long id) {
        return this.forumUserRepository.findById(id).orElseThrow(InvalidForumUserIdException::new);
    }

    @Override
    public ForumUser create(String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        return forumUserRepository.save(new ForumUser(name, email, passwordEncoder.encode(password), type, interestId.stream().map(interestService::findById).collect(Collectors.toList()), birthday));
    }

    @Override
    public ForumUser update(Long id, String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        ForumUser forumUser = this.findById(id);
        forumUser.setName(name);
        forumUser.setEmail(email);
        forumUser.setPassword(passwordEncoder.encode(password));
        forumUser.setType(type);
        forumUser.setInterests(interestId.stream().map(interestService::findById).collect(Collectors.toList()));
        forumUser.setBirthday(birthday);

        return this.forumUserRepository.save(forumUser);
    }

    @Override
    public ForumUser delete(Long id) {
        ForumUser forumUser = this.findById(id);

        this.forumUserRepository.delete(forumUser);

        return forumUser;
    }

    @Override
    public List<ForumUser> filter(Long interestId, Integer age) {

        if(interestId != null && age != null) {
            Interest interest = this.interestService.findById(interestId);
            LocalDate date = LocalDate.now().minusYears(age);
            return this.forumUserRepository.findAllByInterestsContainsAndBirthdayBefore(interest, date);
        }

        if(interestId != null) {
            Interest interest = this.interestService.findById(interestId);
            return this.forumUserRepository.findAllByInterestsContains(interest);
        }

        if(age != null) {
            LocalDate date = LocalDate.now().minusYears(age);
            return this.forumUserRepository.findAllByBirthdayBefore(date);
        }

        return this.forumUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ForumUser user = forumUserRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getType().toString())
                .build();
        }
    }
