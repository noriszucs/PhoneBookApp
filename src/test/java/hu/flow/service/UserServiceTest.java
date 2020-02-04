package hu.flow.service;

import hu.flow.models.User;
import hu.flow.models.dto.UserRegisterDTO;
import hu.flow.models.dto.UserReqDTO;
import hu.flow.models.dto.GetUserDTO;
import hu.flow.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setEmail(user.getEmail());
        getUserDTO.setFirstName(user.getFirstName());
        getUserDTO.setLastName(user.getLastName());
        User u = userRepository.findByUsername(userReqDTO.getUsername());
        assertEquals(user.getEmail(), getUserDTO.getEmail());
    }

}
