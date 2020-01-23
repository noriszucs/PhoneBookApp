package hu.flow.models.dto;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {

   // private BCryptPasswordEncoder passwordEncoder;

    private String firstName;
    private String lastName;
    private String username;
   // private String password;
    private String email;
    private String address;
    private LocalDate birthDate;
    private List<Person_phoneNumber> phoneNumbers;

    public User toUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
       // user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setAddress(address);
        user.setBirthDate(birthDate);
        return user;
    }

}
