package org.kristaps.PP12Serveris.controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.kristaps.PP12Serveris.models.UserModel;
import org.kristaps.PP12Serveris.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityReturnValueHandler;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {
        org.springframework.web.bind.annotation.RequestMethod.GET,
        org.springframework.web.bind.annotation.RequestMethod.POST,
        org.springframework.web.bind.annotation.RequestMethod.PUT,
        org.springframework.web.bind.annotation.RequestMethod.DELETE,
        org.springframework.web.bind.annotation.RequestMethod.OPTIONS })
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
