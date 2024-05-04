package tdtu.edu.usersaccountservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tdtu.edu.usersaccountservice.models.entity.User;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer userId;

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private boolean isTeacher;

    private Date dateOfBirth;

    public static UserResponse parseTo(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .isTeacher(user.isTeacher())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
