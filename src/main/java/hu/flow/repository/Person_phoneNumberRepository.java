package hu.flow.repository;

import hu.flow.models.Person_phoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Person_phoneNumberRepository extends JpaRepository<Person_phoneNumber, Long> {
}
