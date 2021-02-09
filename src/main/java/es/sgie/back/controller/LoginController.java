package es.sgie.back.controller;

import es.sgie.back.controller.error.InfoErrorBody;
import es.sgie.back.domain.User;
import es.sgie.back.exception.ServiceException;
import es.sgie.back.security.JwtTokenUtil;
import es.sgie.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for diagnostic center users
 */
@Slf4j
@RestController
@RequestMapping("/api/token")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> createToken(@RequestBody LoginRequest login) {
        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            User user = this.userService.findByPic(login.getUsername());
            if (user.getActive()) {
                final String token = this.jwtTokenUtil.generateToken(user);
                return ResponseEntity.ok().body(new LoginResponse(token));
            }
        } catch (ServiceException e) {
            log.error("Unable to get username: " + login.getUsername());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new InfoErrorBody("Unable to get username: " + login.getUsername(), e));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new InfoErrorBody("Error UserController.getUser", null));
    }

    @GetMapping("/refresh/{id}")
    public ResponseEntity<Object> refreshToken(@PathVariable(name = "id", required = true) UUID id) {
        try {
            User user = this.userService.findById(id);
            if (user.getActive()) {
                final String token = this.jwtTokenUtil.generateToken(user);
                return ResponseEntity.ok().body(new LoginResponse(token));
            }
        } catch (ServiceException e) {
            log.error("Unable to get user ID: " + id);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new InfoErrorBody("Unable to get user ID: " + id, e));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new InfoErrorBody("Error UserController.getUser", null));
    }

    /**
     * Internal class for Login - Request
     */
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Internal class for Login - Response
     */
    public static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }
    }
}
