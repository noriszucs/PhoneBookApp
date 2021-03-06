package hu.flow.rest;

import hu.flow.models.PersonPhoneNumber;
import hu.flow.models.dto.PPNumDTO;
import hu.flow.models.dto.UserResDTO;
import hu.flow.service.PersonPhoneNumberService;
import hu.flow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PersonPhoneNumberResource {

    private final UserService userService;
    private final PersonPhoneNumberService person_phoneNumberService;

    @GetMapping("/contact")
    public List<PersonPhoneNumber> findAll() {
        return person_phoneNumberService.findAll();
    }

    @GetMapping("/me")
    public UserResDTO findMe() {
        return userService.findMe();
    }

    @GetMapping("/mycontacts")
    public List<PersonPhoneNumber> findMyContact() {
        return userService.findMyContacts();
    }

    @GetMapping("/contact/id/{id}")
    public PersonPhoneNumber findOne(@PathVariable Long id) {
        return person_phoneNumberService.findOne(id);
    }

    @GetMapping("/contact/phone/{number}")
    public PersonPhoneNumber findOnePhone(@PathVariable int number) {
        return person_phoneNumberService.findOnePhone(number);
    }

    @GetMapping("/contact/name/{name}")
    public PersonPhoneNumber findOneContact(@PathVariable String name) {
        return person_phoneNumberService.findOneContact(name);
    }

    @PostMapping("/users/id/{id}/contact")
    public ResponseEntity<PPNumDTO> createContact(@PathVariable Long id, @RequestBody PPNumDTO ppNumDTO) {
        System.out.println(ppNumDTO);
        PersonPhoneNumber person_phoneNumber = person_phoneNumberService.create(id, ppNumDTO);
        ppNumDTO.person_PhnNumDTOFromPerson_PhoneNumber(person_phoneNumber);
        return ResponseEntity.ok(ppNumDTO);
    }

    @PutMapping("/contact")
    public ResponseEntity update(@RequestBody PPNumDTO ppNumDTO) {
        return person_phoneNumberService.update(ppNumDTO);
    }

    @DeleteMapping("/contact/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        person_phoneNumberService.deleteByName(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/contact/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        person_phoneNumberService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
