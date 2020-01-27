package hu.flow.models.dto;

import hu.flow.models.Group;
import hu.flow.models.Person_phoneNumber;
import hu.flow.models.PhoneType;
import hu.flow.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class P_PNumDTO {

    private Long id;
    private Long userId;
    private String name;
    private int countryCode;
    private int areaCode;
    private int number;
    private PhoneType phoneType;
    private Group group;
    private List<Long> userIDs;

    public void person_PhnNumDTOFromPerson_PhoneNumber(Person_phoneNumber person_phoneNumber) {
        this.id = person_phoneNumber.getId();
        this.name = person_phoneNumber.getName();
        this.countryCode = person_phoneNumber.getCountryCode();
        this.areaCode = person_phoneNumber.getAreaCode();
        this.number = person_phoneNumber.getNumber();
        this.phoneType = person_phoneNumber.getPhoneType();
        this.group = person_phoneNumber.getGroup();
        if (person_phoneNumber.getUser() != null) {
            this.userId = person_phoneNumber.getUser().getId();
        } else this.userId = null;
        //this.userIDs = person_phoneNumber.getUsers().stream().map(User::getId).collect(Collectors.toList());
    }
}
