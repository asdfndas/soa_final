package tdtu.edu.usersaccountservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.usersaccountservice.models.dto.UserRequest;
import tdtu.edu.usersaccountservice.models.dto.UserResponse;
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
        UserResponse response = new UserResponse();

        userService.createUser(userRequest);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(response, responseHeaders, HttpStatus.CREATED);

    }

    @GetMapping(path = "/get-user-by-email/v1")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getUserByEmail(String email) {
        List<UserResponse> users = userService.getUserByEmail(email);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/get-user-by-userId/v1")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getUserByUserId(int userId) {
        UserResponse userResponse = userService.getUserById(userId);

        if (userResponse != null) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        UserResponse respObject = userService.updateUserByEmail(request.getFirstName(), request.getLastName(), request.getUserName(), request.getEmail());
        HttpHeaders responseHeaders = new HttpHeaders();

        if (respObject != null) {
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>(respObject, responseHeaders, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<> (responseHeaders, HttpStatus.NOT_FOUND);
    }
}
