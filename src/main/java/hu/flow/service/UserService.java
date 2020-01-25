package hu.flow.service;

import hu.flow.models.User;
import hu.flow.models.dto.UserReqDTO;
import hu.flow.models.dto.UserResDTO;
import hu.flow.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserResDTO getUser(UserReqDTO userReqDTO) {
        if(userRepository.findByUsername(userReqDTO.getUsername()) != null) {
            User u = userRepository.findByUsername(userReqDTO.getUsername());
            if(BCrypt.checkpw(userReqDTO.getPassword(), u.getPassword())) {
                UserResDTO uDTO = new UserResDTO();
                uDTO.setEmail(u.getEmail());
                uDTO.setFirstName(u.getFirstName());
                uDTO.setLastName(u.getLastName());
                return uDTO;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> save(User user) {   // create
        if (user.getId() == null) {
            String passw = passwordEncoder.encode(user.getPassword());
            user.setPassword(passw);
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Void> update(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            User existingUser = userRepository.findByUsername(user.getUsername());
            String newPassw = passwordEncoder.encode(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPassword(newPassw);
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User cannot be found");
        }
        return ResponseEntity.ok().build();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByName(String username) {
        userRepository.deleteByUsername(username);
    }

}
