package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);

}
