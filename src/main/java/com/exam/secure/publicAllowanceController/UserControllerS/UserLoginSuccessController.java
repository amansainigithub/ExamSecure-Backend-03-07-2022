package com.exam.secure.publicAllowanceController.UserControllerS;

import com.exam.secure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/cred/oex/secure")
@PreAuthorize("hasRole('USER')")
public class UserLoginSuccessController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/currentUser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userAccess(Principal principal) {

        return ResponseEntity.ok( this.userRepository.findByUsername( principal.getName()));
    }

}
