package engine.service;

import engine.model.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void register(String email, String password) {
        if (findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
    }
}
