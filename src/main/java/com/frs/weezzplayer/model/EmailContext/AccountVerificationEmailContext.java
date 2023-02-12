package com.frs.weezzplayer.model.EmailContext;

import com.frs.weezzplayer.entity.User;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {
    private String token;


    @Override
    public <T> void init(T context){
        User customer = (User) context;
        put("firstName", customer.getFirstname());
        setTemplateLocation("email-verfication");
        setSubject("Complete your registration");
        setFrom("Frs-reply@frsdev.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }


    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/api/auth/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }

}
