package hu.flow.service;

import hu.flow.exception.ValidationException;
import hu.flow.models.Role;
import hu.flow.models.User;
import hu.flow.models.dto.UserRegisterDTO;
import hu.flow.models.dto.UserReqDTO;
import hu.flow.models.dto.GetUserDTO;
import hu.flow.models.dto.UserResDTO;
import hu.flow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    public GetUserDTO getUser(UserReqDTO userReqDTO) {
        if(userRepository.findByUsername(userReqDTO.getUsername()) != null) {
            User u = userRepository.findByUsername(userReqDTO.getUsername());
            if(BCrypt.checkpw(userReqDTO.getPassword(), u.getPassword())) {
                GetUserDTO uDTO = new GetUserDTO();
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

    public UserRegisterDTO save(User user) {   // create
        if (user.getId() == null) {
            String passw = passwordEncoder.encode(user.getPassword());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setPassword(passw);
            user.setUsername(user.getUsername());
            user.setEmail(user.getEmail());
            user.setBirthDate(user.getBirthDate());
            user.setRole(Role.USER);
            userRepository.save(user);
            var savedUser = userRepository.save(user);
            return UserRegisterDTO.fromUser(savedUser);
        } else {
            throw new RuntimeException("user exist");
        }
    }

    public ResponseEntity<Void> update(UserRegisterDTO userRegisterDTO) {
        if(userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            User existingUser = userRepository.findByUsername(userRegisterDTO.getUsername());
            String newPassw = passwordEncoder.encode(userRegisterDTO.getPassword());
            existingUser.setFirstName(userRegisterDTO.getFirstName());
            existingUser.setLastName(userRegisterDTO.getLastName());
            existingUser.setPassword(newPassw);
            existingUser.setEmail(userRegisterDTO.getEmail());
            existingUser.setBirthDate(userRegisterDTO.getBirthDate());
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User cannot be found.");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByName(String username) {
        userRepository.deleteByUsername(username);
    }

    public List<String> findMyContacts() {
        String currentUsername = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::getName).orElse("");
        return new UserResDTO(userRepository.findByUsername(currentUsername)).getPhoneNumbers();
    }

    /*public UserResDTO findMyContacts() {
        String currentUsername = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(Authentication::getName).orElse("");
        return new UserResDTO(userRepository.findByUsername(currentUsername));
    }*/
}
