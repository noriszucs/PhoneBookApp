package hu.flow.models.dto;

import hu.flow.models.Person_phoneNumber;
import hu.flow.models.Role;
import hu.flow.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Iterator;
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
    private String address;
    private LocalDate birthDate;
    private Role role;
    private List<Long> phoneNumberIds;


    public UserResDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.birthDate = user.getBirthDate();
        this.role = user.getRole();
        if (user.getPhoneNumbers() == null) {
            this.phoneNumberIds = null;
        } else {
            this.phoneNumberIds = user.getPhoneNumbers().stream().map(Person_phoneNumber::getId).collect(Collectors.toList());
        }
    }
}
