package org.kristaps.PP12Serveris.repository;

import org.kristaps.PP12Serveris.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Boolean existsByEmail(String email);

    UserModel findByEmail(String email);
}
