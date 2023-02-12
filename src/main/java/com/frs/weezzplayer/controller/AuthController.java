package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.exception.InvalidTokenException;
import com.frs.weezzplayer.exception.UserLoginException;
import com.frs.weezzplayer.model.LoginRequestDTO;
import com.frs.weezzplayer.model.LoginResponseDTO;
import com.frs.weezzplayer.model.RegisterRequestDTO;
import com.frs.weezzplayer.repository.UserRepository;
import com.frs.weezzplayer.service.AuthDetailsService;
import com.frs.weezzplayer.service.UserDetailsImpl;
import com.frs.weezzplayer.service.UserDetailsServiceImpl;
import com.frs.weezzplayer.utility.JwtUtil;
import com.sun.security.auth.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final 	AuthenticationManager authenticationManager;


    private final AuthDetailsService authService;
    private final JwtUtil tokenUtil;



    @PostMapping({"/login"})
    public  ResponseEntity<?> createJwtToken(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = tokenUtil.generateToken(authentication);

        return  ResponseEntity.ok(new LoginResponseDTO(jwt,userDetails.getId()));



    }

    @PostMapping({"/register"})
    public ResponseEntity<User> CreateNewUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        authService.RegisterUser(registerRequestDTO);
        return new ResponseEntity<>(authService.getByEmail((registerRequestDTO.getEmail())), HttpStatus.OK);
    }
    @GetMapping("/verify")
    public String confirm(@RequestParam("token") String token) {
        try {
            authService.verifyUser(token);
        }catch (InvalidTokenException e) {
            return "REDIRECT_LOGIN";        }
        return "verify" ;
    }
    @GetMapping( "/current")
    public Object currentUser(Authentication authentication) {
        String username;
        if (authentication.getPrincipal() instanceof UserDetails) {
            return authentication.getPrincipal();
        }
        return null;
    }





}
