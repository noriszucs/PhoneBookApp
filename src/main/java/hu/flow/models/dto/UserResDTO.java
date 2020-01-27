package hu.flow.models.dto;

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
    private Iterator<String> contactName;

    public void userDTOFromUser(User user) {
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
            this.phoneNumberIds = user.getPhoneNumbers().stream().map(x -> x.getId()).collect(Collectors.toList());
            /*contactName.hasNext();
            String names = contactName.next();
            System.out.println(names);*/
            //this.contactName.forEachRemaining(System.out::println);
        }
    }
}
