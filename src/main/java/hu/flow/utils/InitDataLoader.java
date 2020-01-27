package hu.flow.utils;

import hu.flow.models.Role;
import hu.flow.models.User;
import hu.flow.repository.Person_phoneNumberRepository;
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

    private final Person_phoneNumberRepository person_phoneNumberRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder().username("nori").password(passwordEncoder.encode("123")).role(Role.ADMIN).build());
        userRepository.save(User.builder().username("zoli").password(passwordEncoder.encode("123")).role(Role.USER).build());

    }
}
