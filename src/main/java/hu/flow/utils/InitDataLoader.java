package hu.flow.utils;

import hu.flow.models.*;
import hu.flow.repository.PersonPhoneNumberRepository;
import hu.flow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@Transactional
@AllArgsConstructor
public class InitDataLoader {

    private final PersonPhoneNumberRepository person_phoneNumberRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder().username("nori").password(passwordEncoder.encode("123")).email("noriadmin@gmail.com").role(Role.ADMIN).build());
        userRepository.save(User.builder().firstName("Vivien").lastName("Kovács").username("vivi").password(passwordEncoder.encode("123")).email("vivitester@gmail.com").role(Role.USER).build());
        person_phoneNumberRepository.save(PersonPhoneNumber.builder().name("Anya").countryCode(36).areaCode(30).number(2525144).group(Group.FAMILY).phoneType(PhoneType.MOBIL).build());
        person_phoneNumberRepository.save(PersonPhoneNumber.builder().name("Roli").countryCode(36).areaCode(20).number(3654699).group(Group.FRIEND).phoneType(PhoneType.MOBIL).build());
        person_phoneNumberRepository.save(PersonPhoneNumber.builder().name("Mama").countryCode(36).areaCode(62).number(244666).group(Group.FAMILY).phoneType(PhoneType.HOME).build());

    }
}