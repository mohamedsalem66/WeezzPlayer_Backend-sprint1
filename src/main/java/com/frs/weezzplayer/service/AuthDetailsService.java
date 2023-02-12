package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.ConfirmationToken;
import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.Reservation.BalanceAccount;
import com.frs.weezzplayer.entity.Role;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.exception.ConflictException;
import com.frs.weezzplayer.exception.InvalidTokenException;
import com.frs.weezzplayer.model.ERole;
import com.frs.weezzplayer.model.EmailContext.AccountVerificationEmailContext;
import com.frs.weezzplayer.model.LoginRequestDTO;
import com.frs.weezzplayer.model.LoginResponseDTO;
import com.frs.weezzplayer.model.RegisterRequestDTO;
import com.frs.weezzplayer.repository.OrganizationRepository;
import com.frs.weezzplayer.repository.Reservation.BalanceAccountRepository;
import com.frs.weezzplayer.repository.RoleRepository;
import com.frs.weezzplayer.repository.UserRepository;
import com.frs.weezzplayer.repository.interfaces.AuthService;
import com.frs.weezzplayer.repository.interfaces.EmailService;
import com.frs.weezzplayer.utility.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthDetailsService  {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailSender;
    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;
    private final BalanceAccountRepository balanceAccountRepository;
    @Value("${site.base.url.https}")
    private String baseURL;

    public void RegisterUser(RegisterRequestDTO registerRequestDTO) {

        if(userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new ConflictException("Email [email: " + registerRequestDTO.getEmail() + "] is already taken");
        }
        User user = new User();
        BalanceAccount account= new BalanceAccount();
        balanceAccountRepository.save((account));
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setUsername(registerRequestDTO.getUsername());
        user.setFirstname(registerRequestDTO.getFirstname());
        user.setLastname(registerRequestDTO.getLastname());
        user.setEmail(registerRequestDTO.getEmail());
        user.setIsEnabled(false);
        user.setAccount(account);


        String strRoles = registerRequestDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == "Personnel") {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setRoles(roles);


        }
         else{
            Role CompanyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(CompanyRole);
            Organization org=registerRequestDTO.getOrganization();
             Organization organization=new Organization(org.getName(),org.getLocation(),org.getIndustry());
            organizationRepository.save(organization);
            user.setRoles(roles);
            user.setAccount(account);
            user.setOrganization(registerRequestDTO.getOrganization());

         }

        userRepository.save(user);

        //send token Confirmation
        sendConfirmationEmail(user);
    }

    public Optional<Authentication> authenticateUser(LoginRequestDTO loginRequest) {
        return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword())));
    }

    public void sendConfirmationEmail(User user)

    {
        ConfirmationToken  SecureToken=confirmationTokenService.createConfirmationToken(user);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(SecureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, SecureToken.getToken());
        try {
            emailSender.sendmail(emailContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyUser(String token) throws InvalidTokenException {
        ConfirmationToken secureToken = confirmationTokenService.findToken(token).orElseThrow(() ->
                new IllegalStateException("token not found"));
        if(secureToken.isExpired() ||  !StringUtils.equals(token, secureToken.getToken()) ){
            throw new InvalidTokenException("Token is not valid");        }


        User user = userRepository.getById(secureToken.getUser().getId());
        if(Objects.isNull(user)){
            return false;
        }
        user.setIsEnabled(true);
        userRepository.save(user);

        confirmationTokenService.removeToken(secureToken);
        return true;
    }
   public User getByEmail(String email){
        return userRepository.findByEmail(email) ;
    }
    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }
    public User getUserById(Long id){
        return this.userRepository.findById(id).get();
    }
    public User updateUser(User user){
        return this.userRepository.save(user);
    }































   /* public boolean findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username,email)!=null ? true :false;

    }*/

}



