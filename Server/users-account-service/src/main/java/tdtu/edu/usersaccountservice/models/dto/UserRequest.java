package tdtu.edu.usersaccountservice.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private boolean isTeacher;

    private Date dateOfBirth;

}
