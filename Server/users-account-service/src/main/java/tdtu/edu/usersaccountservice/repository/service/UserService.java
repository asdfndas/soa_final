package tdtu.edu.usersaccountservice.repository.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.usersaccountservice.models.dto.UserRequest;
import tdtu.edu.usersaccountservice.models.dto.UserResponse;
import tdtu.edu.usersaccountservice.models.entity.User;
import tdtu.edu.usersaccountservice.models.entity.UserRole;
import tdtu.edu.usersaccountservice.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public Integer createUser(UserRequest userRequest) {
        Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        Date currentDate = Date.from(instant);
        UserRole createRole = userRoleService.addRoleUser(new UserRole("Student", currentDate));

        return userRepository.saveUser(
                userRequest.getUserName(), userRequest.getEmail(), userRequest.getFirstName(),
                userRequest.getLastName(), userRequest.getGender(), userRequest.isTeacher(),
                userRequest.getDateOfBirth(), createRole.getId()
        );
    }

    public boolean existUser(Integer userId) {
        return userRepository.existsByUserId(userId);
    }

    public List<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public User getUserById(int userId) {
        Optional<User> result = userRepository.getUserByUserId(userId);
        return result.orElse(null);
    }

    public void updateUserByEmail(String firstName, String lastName, String userName, String email) {
        userRepository.updateUserByEmail(firstName, lastName, userName, email);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .isTeacher(user.isTeacher())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
