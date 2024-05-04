package tdtu.edu.usersaccountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.edu.usersaccountservice.models.entity.UserRole;
import tdtu.edu.usersaccountservice.repository.service.UserRoleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role-user")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping("/test")
    public ResponseEntity<?> testAuthorized() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/add-role")
    public ResponseEntity<?> addRoleUser(UserRole userRole) {
        if (userRoleService.addRoleUser(userRole) != null) {
            return new ResponseEntity<>(userRole, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


}
