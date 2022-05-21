package com.rahmatavg.api.login.repository;

import com.rahmatavg.api.login.model.GenerateToken;

public interface GenerateTokenRepository {
    int saveToken(GenerateToken generateToken);
    String getToken(Long userId);

    int deleteToken(Long userId);
}
