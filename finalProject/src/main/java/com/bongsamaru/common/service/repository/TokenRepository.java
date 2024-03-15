package com.bongsamaru.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bongsamaru.common.VO.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
