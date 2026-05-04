package org.manuel.portfoliobe.security.service;

import org.manuel.portfoliobe.security.entity.User;
import org.manuel.portfoliobe.security.payload.LoginDto;
import org.manuel.portfoliobe.security.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    void createAdmin();
    User makeAdmin(User user);
}
