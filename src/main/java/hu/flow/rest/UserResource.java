package hu.flow.rest;

import hu.flow.models.User;
import hu.flow.models.dto.UserDTO;
import hu.flow.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @GetMapping("/users/{username}")
    public User findOneUser(@PathVariable String username) {
        return userService.findOneUser(username);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        log.info("User DTO: {}", userDTO);
        userService.save(userDTO);
        return userDTO;
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return userDTO;
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteByName(@PathVariable String username) {
        userService.deleteByName(username);
        return ResponseEntity.ok().build();
    }
}
