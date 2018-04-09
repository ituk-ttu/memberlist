package ee.ituk.memberlist.server.user;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByMemberEmail(email);
    }
}
