package com.navi.nbcampauthenticationfilterexample.repository;

import com.navi.nbcampauthenticationfilterexample.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
