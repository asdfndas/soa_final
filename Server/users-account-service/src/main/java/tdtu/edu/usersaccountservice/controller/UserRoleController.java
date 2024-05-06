package tdtu.edu.usersaccountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.usersaccountservice.models.entity.UserRole;
import tdtu.edu.usersaccountservice.repository.service.UserRoleService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role-user")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping("/get-role-user-by-email")
    public ResponseEntity<?> getRoleUserByEmail(String email) {
        UserRole result = userRoleService.getUserRoleByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add-role")
    public ResponseEntity<?> addRoleUser(UserRole userRole) {
        if (userRoleService.addRoleUser(userRole) != null) {
            return new ResponseEntity<>(userRole, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-role-user")
    public ResponseEntity<?> updateRoleUser(String limitTime, String roleName, Integer id) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse(limitTime);
        userRoleService.updateUserRole(date, roleName, id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

}
