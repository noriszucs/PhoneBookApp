package hu.flow.rest;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.dto.P_PNumDTO;
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

    @GetMapping("/contact/mycontacts/{userId}")
    public List<P_PNumDTO> findMyContact(@PathVariable  Long userId) {
        return person_phoneNumberService.findAllByUserId(userId);
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

    @PostMapping("/contact")
    public ResponseEntity<P_PNumDTO> createContact(@RequestBody P_PNumDTO ppNumDTO) {
        Person_phoneNumber person_phoneNumber = person_phoneNumberService.create(ppNumDTO);
        ppNumDTO.person_PhnNumDTOFromPerson_PhoneNumber(person_phoneNumber);
        return ResponseEntity.ok(ppNumDTO);
    }

/*    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.saveTask(taskDTO);
        taskDTO.taskDTOFromTask(task);
        return ResponseEntity.ok(taskDTO);
    }*/

    @PutMapping("/contact")
    public ResponseEntity update(@RequestBody P_PNumDTO ppNumDTO) {
        return person_phoneNumberService.update(ppNumDTO);
    }

    @DeleteMapping("/contact/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        person_phoneNumberService.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
