package com.zoro.interviewprep.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // ✅ SRP: Only handles DB logic for User.
    // ✅ ISP: Exposes only relevant DB operations, no unrelated methods.
}
