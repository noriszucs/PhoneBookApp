package hu.flow.service;

import hu.flow.models.PersonPhoneNumber;
import hu.flow.models.dto.PPNumDTO;
import hu.flow.repository.PersonPhoneNumberRepository;
import hu.flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class PersonPhoneNumberService {

    @Autowired
    private PersonPhoneNumberRepository person_phnRepository;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    public PersonPhoneNumberService() {
    }

    public List<PersonPhoneNumber> findAll() {
        return person_phnRepository.findAll();
    }

    public PersonPhoneNumber findOne(Long id) {
        return person_phnRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PersonPhoneNumber findOnePhone(int number) {
        return  person_phnRepository.findByNumber(number);
    }

    public PersonPhoneNumber findOneContact(String name) {
        return person_phnRepository.findByName(name);
    }

    public PersonPhoneNumber create( Long id ,PPNumDTO ppNumDTO) {
        PersonPhoneNumber person_phoneNumber = new PersonPhoneNumber();
        person_phoneNumber.ppNumFromPpNumDTO(ppNumDTO);
        person_phoneNumber.setUser(userRepository.findById(id).get());
        return person_phnRepository.save(person_phoneNumber);
    }

    public ResponseEntity<Void> update(PPNumDTO ppNumDTO) {
        if(person_phnRepository.findByName(ppNumDTO.getName()) != null) {
            PersonPhoneNumber existingContact = findOneContact(ppNumDTO.getName());
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
