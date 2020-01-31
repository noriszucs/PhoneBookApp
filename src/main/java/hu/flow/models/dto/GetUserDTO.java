package hu.flow.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GetUserDTO {

    private String email;
    private String firstName;
    private String lastName;
}
