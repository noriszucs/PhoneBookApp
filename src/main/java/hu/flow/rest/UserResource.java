package hu.flow.rest;

import hu.flow.models.User;
import hu.flow.models.dto.*;
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

    @GetMapping("/users/id/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @GetMapping("/users/user/{username}")
    public User findOneUser(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/users/login")
    public GetUserDTO getUserDTO(@RequestBody UserReqDTO userReqDTO) {
        return userService.getUser(userReqDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDTO> createUser(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.save(user),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/userupdate")
    public ResponseEntity update(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.update(userRegisterDTO);
    }

    @DeleteMapping("/user/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/user/{username}")
    public ResponseEntity<Void> deleteByName(@PathVariable String username) {
        userService.deleteByName(username);
        return ResponseEntity.ok().build();
    }
}
