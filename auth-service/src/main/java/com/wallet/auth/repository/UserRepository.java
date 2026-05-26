package com.wallet.auth.repository;

import com.wallet.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByName(String name);
}
