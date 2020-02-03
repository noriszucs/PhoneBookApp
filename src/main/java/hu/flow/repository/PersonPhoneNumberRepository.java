package hu.flow.repository;

import hu.flow.models.PersonPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPhoneNumberRepository extends JpaRepository<PersonPhoneNumber, Long> {
    PersonPhoneNumber findByNumber(int number);

    PersonPhoneNumber findByName(String name);

    void deleteByName(String name);
}
