package hu.flow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "phoneNumbers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person_phoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int countryCode;

    @Column
    private int areaCode;

    @Column
    private int number;

    @Enumerated(value = EnumType.STRING)
    private PhoneType phoneType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "_group")
    private Group group;

    @ManyToMany(mappedBy = "phoneNumbers")
    private List<User> users;
}
