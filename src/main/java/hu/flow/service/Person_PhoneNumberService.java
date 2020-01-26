package hu.flow.service;

import hu.flow.models.Person_phoneNumber;
import hu.flow.repository.Person_phoneNumberRepository;
import hu.flow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class Person_PhoneNumberService {

    private final Person_phoneNumberRepository person_phnRepository;
    private final UserRepository userRepository;

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

    public ResponseEntity create(Person_phoneNumber person_phoneNumber) {
        if (person_phoneNumber.getNumber() != person_phoneNumber.getNumber()) {
            person_phnRepository.save(person_phoneNumber);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new RuntimeException("Already exist this phonenumber.");
        }
    }

    public ResponseEntity<Void> update(Person_phoneNumber person_phoneNumber) {
        if(person_phnRepository.findByName(person_phoneNumber.getName()) != null) {
            Person_phoneNumber existingPhnNumber = person_phnRepository.findByName(person_phoneNumber.getName());
            existingPhnNumber.setCountryCode(person_phoneNumber.getCountryCode());
            existingPhnNumber.setAreaCode(person_phoneNumber.getAreaCode());
            existingPhnNumber.setNumber(person_phoneNumber.getNumber());
            existingPhnNumber.setName(person_phoneNumber.getName());
            existingPhnNumber.setPhoneType(person_phoneNumber.getPhoneType());
            existingPhnNumber.setGroup(person_phoneNumber.getGroup());
            person_phnRepository.save(existingPhnNumber);
        } else {
            throw new RuntimeException("This phonenumber cannot be found.");
        }
        return ResponseEntity.ok().build();
    }

    public void deleteByName(String name) {
        person_phnRepository.deleteByName(name);
    }
}
