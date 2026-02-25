package org.kristaps.PP12Serveris.services;

import org.kristaps.PP12Serveris.models.UserModel;
import org.kristaps.PP12Serveris.repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ManyToOne, OneToMany, ManyToMany, OneToOne
    // Functional interface - lambda, stream, filter, map, reduce, forEach,
    // anonymous class

    public Long createUser(UserModel user) {
        user.getHobbies().forEach(hobby -> hobby.setUser(user));
        UserModel savedUser = userRepository.save(user);
        return savedUser.getId();

    }

    public Boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserModel getUserById(Long userID) {
        UserModel useris = userRepository.findById(userID).orElse(null);
        System.out.println(useris);
        return useris;
    }

    public UserModel deleteUserById(Long userID) {
        UserModel useris = userRepository.findById(userID).orElse(null);
        if (useris != null) {
            userRepository.deleteById(userID);
        }
        return useris;
    }
}
