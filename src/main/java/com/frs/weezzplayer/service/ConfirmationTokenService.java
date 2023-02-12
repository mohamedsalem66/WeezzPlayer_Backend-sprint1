package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.ConfirmationToken;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmationTokenService {
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository){

        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Value("${secure.token.validity}")
    private Long tokenValidityInSeconds;

        public ConfirmationToken createConfirmationToken(User user){
        String tokenValue= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                tokenValue, LocalDateTime.now(), LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()),user
        );
       return confirmationTokenRepository.save(confirmationToken);
    }


    public Optional<ConfirmationToken> findToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }



    public void removeToken(ConfirmationToken token){
            confirmationTokenRepository.delete(token);
    }
    public Long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }


}
