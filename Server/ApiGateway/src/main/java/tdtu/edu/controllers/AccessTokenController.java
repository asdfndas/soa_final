package tdtu.edu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AccessTokenController {
    @Value("${okta.client.token}")
    private String jwtToken;

    @GetMapping("/access-token")
    public ResponseEntity<?> getAccessToken() {
        Map<String, String> token = new HashMap<>();
        token.put("Bearer", jwtToken);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
