package hu.flow.rest;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.User;
import hu.flow.service.Person_PhoneNumberService;
import hu.flow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Person_phoneNumberResource {

    private final UserService userService;
    private final Person_PhoneNumberService person_phoneNumberService;

    @GetMapping("/contact")
    public List<Person_phoneNumber> findAll() {
        return person_phoneNumberService.findAll();
    }

    @GetMapping("/contact/id/{id}")
    public Person_phoneNumber findOne(@PathVariable Long id) {
        return person_phoneNumberService.findOne(id);
    }

    @GetMapping("/contact/phone/{number}")
    public Person_phoneNumber findOnePhone(@PathVariable int number) {
        return person_phoneNumberService.findOnePhone(number);
    }

    @GetMapping("/contact/name/{name}")
    public Person_phoneNumber findOneContact(@PathVariable String name) {
        return person_phoneNumberService.findOneContact(name);
    }

    @PostMapping("/me")
    public ResponseEntity createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/contact")
    public ResponseEntity createContact(@RequestBody Person_phoneNumber person_phoneNumber) {
        return person_phoneNumberService.create(person_phoneNumber);
    }

    @PutMapping("/contact")
    public ResponseEntity update(@RequestBody Person_phoneNumber person_phoneNumber) {
        return person_phoneNumberService.update(person_phoneNumber);
    }

    @DeleteMapping("/contact/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        person_phoneNumberService.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
