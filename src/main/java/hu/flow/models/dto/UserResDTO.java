package hu.flow.models.dto;

import hu.flow.models.PersonPhoneNumber;
import hu.flow.models.Role;
import hu.flow.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserResDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocalDate birthDate;
    private Role role;
    //private List<Long> phoneNumberIds;
    private List<PersonPhoneNumber> phoneNumbers;


    public UserResDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
        this.role = user.getRole();
        if (user.getPhoneNumbers() == null) {
            this.phoneNumbers = null;
        } else {
            //this.phoneNumberIds = user.getPhoneNumbers().stream().map(PersonPhoneNumber::getId).collect(Collectors.toList());
            this.phoneNumbers = user.getPhoneNumbers();//.stream().map(x -> x.getName() + " " + "+" + x.getCountryCode() + x.getAreaCode() + x.getNumber() + " " + x.getPhoneType() + " " + x.getGroup()).collect(Collectors.toList());

        }
    }
}
