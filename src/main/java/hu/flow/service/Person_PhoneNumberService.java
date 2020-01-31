package hu.flow.service;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.dto.P_PNumDTO;
import hu.flow.repository.Person_phoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;


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
        return person_phnRepository.save(person_phoneNumber);
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
