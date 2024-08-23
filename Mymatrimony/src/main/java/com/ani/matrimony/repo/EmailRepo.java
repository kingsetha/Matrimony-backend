package com.ani.matrimony.repo;
import com.ani.matrimony.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepo extends JpaRepository<Email, Integer> {
}

