package es.sgie.back.controller;

import es.sgie.back.controller.error.InfoErrorBody;
import es.sgie.back.domain.User;
import es.sgie.back.exception.ServiceException;
import es.sgie.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Api for users
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(this.userService.findById(id));
        } catch (ServiceException e) {
            log.error("Error UserController.getUser", e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new InfoErrorBody("Error UserController.getUser", e));
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADM')")
    public ResponseEntity<List<User>> getAllUsers() {
        return null;
    }

}
