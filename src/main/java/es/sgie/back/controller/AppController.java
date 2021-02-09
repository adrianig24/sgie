package es.sgie.back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for app users
 */
@Slf4j
@RestController
@RequestMapping("/api/app")
@PreAuthorize("hasAnyRole('ROLE_ADM', 'ROLE_CTM')")
public class AppController {

    @GetMapping("/echo")
    public ResponseEntity<String> echoApp() {
        return new ResponseEntity<String>("Controller App", HttpStatus.OK);
    }

}
