package com.ani.matrimony.repo;

import com.ani.matrimony.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDetailRepo extends JpaRepository<Email, Long> {
    // Custom query methods can be defined here if needed
}
