package com.batuhan.eindprojectBatuhan.repository;

import com.batuhan.eindprojectBatuhan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
