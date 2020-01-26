package hu.flow.service;

import hu.flow.models.User;
import hu.flow.models.dto.UserReqDTO;
import hu.flow.models.dto.UserResDTO;
import hu.flow.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    BCryptPasswordEncoder passwordEncoder;
    UserService userService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserDto(@Mock UserRepository userRepository) {
        UserReqDTO userReqDTO = new UserReqDTO();
        User user = User.builder().firstName("Szucs").lastName("Nora").username("nori").password("noriASD").email("valami@valami.com").build();
        UserResDTO userResDTO = new UserResDTO();
        userResDTO.setEmail(user.getEmail());
        userResDTO.setFirstName(user.getFirstName());
        userResDTO.setLastName(user.getLastName());
        User u = userRepository.findByUsername(userReqDTO.getUsername());
        assertEquals(user.getEmail(), userResDTO.getEmail());
    }

    @Test
    public void save(@Mock UserRepository userRepository) {
        User user = User.builder().username("nori").password("noriASD").build();
        new UserService(userRepository, passwordEncoder).save(user);
        assertNotEquals("noriDSA", user.getPassword());
    }

    @Test
    public void update(@Mock UserRepository userRepository) {
        User olduser = User.builder().firstName("Szucs").lastName("Nora").username("nori").password("noriASD").email("valami@valami.com").build();
        User updateduser = User.builder().firstName("Szucs").lastName("Nora").username("nori").password("cica").email("valami@valami.com").build();
        Mockito.when(userRepository.findByUsername("nori")).thenReturn(olduser);
        new UserService(userRepository, passwordEncoder).update(updateduser);
        assertEquals("cica", updateduser.getPassword());
    }
}
