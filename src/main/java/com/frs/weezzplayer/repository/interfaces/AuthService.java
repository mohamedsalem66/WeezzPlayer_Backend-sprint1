package com.frs.weezzplayer.repository.interfaces;

import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.exception.InvalidTokenException;
import com.frs.weezzplayer.model.LoginRequestDTO;
import com.frs.weezzplayer.model.LoginResponseDTO;
import com.frs.weezzplayer.model.RegisterRequestDTO;

public interface AuthService {
    void RegisterUser(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO);
    void sendConfirmationEmail(User user);
    boolean verifyUser(String token) throws InvalidTokenException;
}
