package com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
