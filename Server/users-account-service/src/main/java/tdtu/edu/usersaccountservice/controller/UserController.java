package tdtu.edu.usersaccountservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.usersaccountservice.models.dto.UserRequest;
import tdtu.edu.usersaccountservice.models.dto.UserResponse;
import tdtu.edu.usersaccountservice.models.entity.User;
import tdtu.edu.usersaccountservice.repository.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_add:user')")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        List<User> resp = userService.getUserByEmail(userRequest.getEmail());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get-user-by-email/v1")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getUserByEmail(String email) {
        List<User> users = userService.getUserByEmail(email);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/get-user-by-userId/v1")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getUserByUserId(int userId) {
        User response = userService.getUserById(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/get-exist-user/v1")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> checkExistUser(Integer userId) {
        Map<String, Boolean> result = new HashMap<>();
        if (userService.existUser(userId)) {
            result.put("isExist", true);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result.put("isExist", false);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-user/v1")
    @PreAuthorize("hasAuthority('SCOPE_update:user')")
    public ResponseEntity<?> updateUserByEmailV1(@RequestBody UserRequest request) {
        userService.updateUserByEmail(request.getFirstName(), request.getLastName(), request.getUserName(), request.getEmail());
//        List<User> resp = userService.getUserByEmail(request.getEmail());
        return new ResponseEntity<> (HttpStatus.OK);
    }

}
