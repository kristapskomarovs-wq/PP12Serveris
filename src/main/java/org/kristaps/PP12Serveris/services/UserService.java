package org.kristaps.PP12Serveris.services;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.kristaps.PP12Serveris.models.UserModel;
import org.kristaps.PP12Serveris.repository.ShopRepository;
import org.kristaps.PP12Serveris.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ManyToOne, OneToMany, ManyToMany, OneToOne
    // Functional interface - lambda, stream, filter, map, reduce, forEach,
    // anonymous class

    public Long createUser(UserModel user) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        user.setEmail(user.getEmail().toLowerCase());

        /*
         * KeyGenerator keyGen = KeyGenerator.getInstance("AES");
         * keyGen.init(256);
         * SecretKey secretKey = keyGen.generateKey();
         * System.out.println("Generated key: " + secretKey);
         * 
         * Cipher cipher = Cipher.getInstance("AES");
         * cipher.init(Cipher.ENCRYPT_MODE, secretKey);
         * byte[] encryptedPassword = cipher.doFinal(user.getPassword().getBytes());
         * user.setPassword(new String(encryptedPassword));
         */

        UserModel savedUser = userRepository.save(user);
        return savedUser.getId();

    }

    public Boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Long signIn(UserModel user) {
        UserModel foundUser = userRepository.findByEmail(user.getEmail().toLowerCase());
        if (!foundUser.getPassword().equals(user.getPassword())) {
            return null;
        }
        return foundUser.getId();
    }

}
