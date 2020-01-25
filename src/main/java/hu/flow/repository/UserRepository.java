package hu.flow.repository;

import hu.flow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);

    public User findByUsername(String username);

    void deleteByUsername(String username);
}
