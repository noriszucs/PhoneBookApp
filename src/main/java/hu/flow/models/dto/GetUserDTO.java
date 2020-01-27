package hu.flow.models.dto;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GetUserDTO {

    private String email;
    private String firstName;
    private String lastName;



}
