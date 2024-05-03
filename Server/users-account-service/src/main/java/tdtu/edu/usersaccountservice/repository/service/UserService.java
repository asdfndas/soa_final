package tdtu.edu.usersaccountservice.repository.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdtu.edu.usersaccountservice.models.dto.UserRequest;
import tdtu.edu.usersaccountservice.models.dto.UserResponse;
import tdtu.edu.usersaccountservice.models.entity.User;
import tdtu.edu.usersaccountservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserRequest userRequest) {
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .isTeacher(userRequest.isTeacher())
                .dateOfBirth(userRequest.getDateOfBirth())
                .build();

        //userRepository.saveUser(user.getUserName(), user.getEmail(), user.getFullName(), user.getDateOfBirth());
        //Test:  User save(User user); repository file
        userRepository.save(user);
        log.info("User email: " + user.getEmail());
    }

    public List<UserResponse> getUserByEmail(String email) {
        List<User> users = userRepository.getUserByEmail(email);
        return users.stream().map(this::mapToUserResponse).toList();
    }

    public UserResponse getUserById(int userId) {
        Optional<User> result = userRepository.getUserByUserId(userId);
        return result.map(UserResponse::parseTo).orElse(null);
    }

    public UserResponse updateUserByEmail(String firstName, String lastName, String userName, String email) {
        return UserResponse.parseTo(userRepository.updateUserByEmail(firstName, lastName, userName, email));
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
