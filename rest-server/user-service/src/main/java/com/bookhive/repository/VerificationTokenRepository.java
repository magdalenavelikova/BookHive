package com.bookhive.repository;

import com.bookhive.model.entities.UserEntity;
import com.bookhive.model.entities.VerificationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    Optional<VerificationToken> findByUser(UserEntity user);

    void deleteByUserId(Long id);
}
