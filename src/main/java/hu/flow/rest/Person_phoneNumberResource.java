package hu.flow.rest;

import hu.flow.models.User;
import hu.flow.models.dto.UserDTO;
import hu.flow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Person_phoneNumberResource {

    /*private UserService userService;

    @PostMapping("/me")
    public UserDTO create(@RequestBody UserDTO userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/me")
    public UserDTO update(@RequestBody UserDTO userDto) {
        return userService.save(userDto);
    }
*/
}
