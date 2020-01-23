package hu.flow.service;

import hu.flow.models.User;
import hu.flow.models.dto.UserDTO;
import hu.flow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private  UserRepository userRepository;
    private  BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User findOneUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void save(UserDTO userDTO) {   // create
        User user = userDTO.toUser();
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(UserDTO userDTO) {
        User user = userDTO.toUser();
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            User existingUser = userRepository.findById(userDTO.getUserId()).get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            User updatedUser = userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User cannot be found");
        }

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByName(String username) {
        userRepository.deleteByUsername(username);
    }
}
