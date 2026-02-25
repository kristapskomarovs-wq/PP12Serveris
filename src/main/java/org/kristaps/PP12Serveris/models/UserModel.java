package org.kristaps.PP12Serveris.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "username")
    String username;
    String email;
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = jakarta.persistence.CascadeType.ALL)
    List<Hobby> hobbies = new ArrayList<>();

}
