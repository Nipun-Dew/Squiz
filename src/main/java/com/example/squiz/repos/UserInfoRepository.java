package com.example.squiz.repos;

import com.example.squiz.entities.UserInfoEB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEB, Long> {
    Optional<UserInfoEB> findByUserName(String userName);
}
