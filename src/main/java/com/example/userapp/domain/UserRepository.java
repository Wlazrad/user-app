package com.example.userapp.domain;

import com.example.userapp.api.NationalIdNumber;
import com.example.userapp.commons.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(@Param("uuid") UUID uuid);

    Optional<User> findFirstByNationalIdNumber(NationalIdNumber nationalIdNumber);

    List<User> findAll();

    default User getByUuid(UUID uuid) {
        return getActive(uuid, findByUuid(uuid));
    }

    private User getActive(UUID uuid, Optional<User> maybeUser) {
        User user = maybeUser.orElseThrow(() -> new UserNotFoundException(uuid));
        return user;
    }

}
