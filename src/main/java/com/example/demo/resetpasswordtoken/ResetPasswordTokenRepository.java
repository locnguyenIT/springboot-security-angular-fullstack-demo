package com.example.demo.resetpasswordtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {

    Optional<ResetPasswordToken> findByToken(String token);


    @Modifying
    @Query("UPDATE ResetPasswordToken rs " +
            "SET rs.confirmedAt = ?2 " +
            "WHERE rs.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
