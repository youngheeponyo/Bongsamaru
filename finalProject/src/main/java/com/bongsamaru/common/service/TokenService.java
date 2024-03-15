package com.bongsamaru.common.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.Token;
import com.bongsamaru.common.service.repository.TokenRepository;

import lombok.extern.log4j.Log4j;
@Service
@Log4j
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    
    
    @Transactional
    public Token createToken(String userId, String tokenValue) {
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(30); // 유효기간을 30분으로 제한.
        Token token = new Token();
        token.setToken(tokenValue);
        token.setUserId(userId);
        token.setExpiryDate(expiryDate);
        tokenRepository.save(token);
        return token;
    }
    
    public boolean validateToken(String token) {
        // DB에서 토큰 조회
        Token tokenEntity = tokenRepository.findByToken(token);
        // 토큰이 존재하지 않는 경우
        if (tokenEntity == null) {
            return false;
        }
        // 토큰의 만료 시간이 현재 시간보다 이전인 경우
        if (tokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        // 여기까지 문제가 없다면 토큰이 유효한 것으로 간주
        return true;
    }
    
    public String getUserIdFromToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null && tokenEntity.getExpiryDate().isAfter(LocalDateTime.now())) {
            return tokenEntity.getUserId();
        }
        return null;
    }
    
}
