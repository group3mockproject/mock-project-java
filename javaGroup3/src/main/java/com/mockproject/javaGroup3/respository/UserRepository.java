package com.mockproject.javaGroup3.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockproject.javaGroup3.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

	Optional<User> findById(Integer id);
}
