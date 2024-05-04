package tdtu.edu.usersaccountservice.repository.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdtu.edu.usersaccountservice.models.entity.UserRole;
import tdtu.edu.usersaccountservice.repository.UserRoleRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleService {
    public final UserRoleRepository userRoleRepository;

    public UserRole addRoleUser(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

}
