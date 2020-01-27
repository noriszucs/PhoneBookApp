package hu.flow.service;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.User;
import hu.flow.models.dto.P_PNumDTO;
import hu.flow.models.dto.UserRegisterDTO;
import hu.flow.repository.Person_phoneNumberRepository;
import hu.flow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class Person_PhoneNumberService {

    @Autowired
    private Person_phoneNumberRepository person_phnRepository;
    @Autowired
    private UserService userService;

    public Person_PhoneNumberService() {
    }

    public List<Person_phoneNumber> findAll() {
        return person_phnRepository.findAll();
    }

    public List<P_PNumDTO> findAllByUserId(Long userId) {
        List<P_PNumDTO> numbers = person_phnRepository.findAllByUserId(userId);
        return numbers;
        //numbers.stream().filter(x -> x.getUserId() == userService.findOne(u)).forEach(System.out::println);
    }

    public Person_phoneNumber findOne(Long id) {
        return person_phnRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Person_phoneNumber findOnePhone(int number) {
        return  person_phnRepository.findByNumber(number);
    }

    public Person_phoneNumber findOneContact(String name) {
        return person_phnRepository.findByName(name);
    }

    public Person_phoneNumber create(P_PNumDTO ppNumDTO) {
        Person_phoneNumber person_phoneNumber = new Person_phoneNumber();
        person_phoneNumber.ppNumFromPpNumDTO(ppNumDTO);
        person_phoneNumber.setUser(userService.findOne(ppNumDTO.getUserId()));
//        person_phoneNumber.setUsers(ppNumDTO.getUserIDs().stream().map(userService::findOne).collect(Collectors.toList()));
        return person_phnRepository.save(person_phoneNumber);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> update(P_PNumDTO ppNumDTO) {
        if(person_phnRepository.findByName(ppNumDTO.getName()) != null) {
            Person_phoneNumber existingContact = findOneContact(ppNumDTO.getName());
            existingContact.setCountryCode(ppNumDTO.getCountryCode());
            existingContact.setAreaCode(ppNumDTO.getAreaCode());
            existingContact.setNumber(ppNumDTO.getNumber());
            existingContact.setName(ppNumDTO.getName());
            existingContact.setPhoneType(ppNumDTO.getPhoneType());
            existingContact.setGroup(ppNumDTO.getGroup());
            person_phnRepository.save(existingContact);
        } else {
            throw new RuntimeException();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public void deleteByName(String name) {
        person_phnRepository.deleteByName(name);
    }
}
