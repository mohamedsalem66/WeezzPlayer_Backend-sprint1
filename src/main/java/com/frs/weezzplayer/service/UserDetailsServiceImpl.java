package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.ConfirmationToken;
import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.exception.NotFoundException;
import com.frs.weezzplayer.model.EmailContext.AccountVerificationEmailContext;
import com.frs.weezzplayer.repository.UserRepository;
import com.frs.weezzplayer.repository.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final EmailService emailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new NotFoundException("user not found");
        }

        return UserDetailsImpl.build(user);
    }
    public User findUserById(Long id)  throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
    public User updateUser(User user){
        return userRepository.save(user);
    }
    public void emailContact(User user){
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setSubject("contact us par Email");
        try {
            emailSender.sendmail(emailContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
