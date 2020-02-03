package hu.flow.models;

import hu.flow.models.dto.PPNumDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "phoneNumbers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int countryCode;

    @Column
    private int areaCode;

    @Column(unique = true)
    private int number;

    @Enumerated(value = EnumType.STRING)
    private PhoneType phoneType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "_group")
    private Group group;

    @ManyToOne
    @JoinColumn
    private User user;

    public void ppNumFromPpNumDTO(PPNumDTO ppNumDTO) {
        this.id = ppNumDTO.getId();
        this.name = ppNumDTO.getName();
        this.countryCode = ppNumDTO.getCountryCode();
        this.areaCode = ppNumDTO.getAreaCode();
        this.number = ppNumDTO.getNumber();
        this.phoneType = ppNumDTO.getPhoneType();
        this.group = ppNumDTO.getGroup();
    }
}
