package org.kristaps.PP12Serveris.controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.kristaps.PP12Serveris.models.UserModel;
import org.kristaps.PP12Serveris.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final UserService userService;

    @PostMapping("/api/v1/saveuser")
    public ResponseEntity<Long> saveUser(@RequestBody UserModel user) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/api/v1/checkemail/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmail(email));
    }

    @PostMapping("/api/v1/signin")
    public ResponseEntity<Long> signIn(@RequestBody UserModel user) {
        Long userId = userService.signIn(user);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1L);
    }

}
