package org.kristaps.PP12Serveris.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {
        org.springframework.web.bind.annotation.RequestMethod.GET,
        org.springframework.web.bind.annotation.RequestMethod.POST,
        org.springframework.web.bind.annotation.RequestMethod.DELETE,
        org.springframework.web.bind.annotation.RequestMethod.OPTIONS })
@RestController
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;

    @GetMapping("/api/v1/check/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.checkEmail(email), HttpStatus.OK);

    }

    @PostMapping("/api/v1/users") // http://localhost:8088/api/v1/users
    public ResponseEntity<Long> createUser(@RequestBody UserModel user) {

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users/{userID}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long userID) {
        UserModel userModel = userService.getUserById(userID);
        if (userModel == null) {
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/users/{userID}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long userID) {
        UserModel deletedUser = userService.deleteUserById(userID);
        if (deletedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedUser.getId(), HttpStatus.NO_CONTENT);
    }

}
