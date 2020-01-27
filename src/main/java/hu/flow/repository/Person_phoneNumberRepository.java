package hu.flow.repository;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.dto.P_PNumDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface Person_phoneNumberRepository extends JpaRepository<Person_phoneNumber, Long> {
    Person_phoneNumber findByNumber(int number);

    Person_phoneNumber findByName(String name);

    public List<P_PNumDTO> findAllByUserId(Long userId);

    void deleteByName(String name);
}
