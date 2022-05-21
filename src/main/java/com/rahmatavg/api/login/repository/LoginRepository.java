package com.rahmatavg.api.login.repository;

import com.rahmatavg.api.login.model.Login;

public interface LoginRepository {
    Login findByEmail(String email);
}
