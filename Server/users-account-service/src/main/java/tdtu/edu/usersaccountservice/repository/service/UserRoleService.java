package tdtu.edu.usersaccountservice.repository.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdtu.edu.usersaccountservice.models.entity.UserRole;
import tdtu.edu.usersaccountservice.repository.UserRoleRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleService {
    public final UserRoleRepository userRoleRepository;

    public UserRole addRoleUser(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public UserRole getUserRoleByEmail(String email) {
        Optional<UserRole> result = userRoleRepository.getUserRoleByEmail(email);
        return result.orElse(null);
    }

    public void updateUserRole(Date limitTime, String roleName, Integer id) {
        userRoleRepository.updateUserRoleById(limitTime, roleName, id);
//        return userRoleRepository.getById(id);
    }
}
