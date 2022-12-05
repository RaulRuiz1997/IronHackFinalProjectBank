package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
