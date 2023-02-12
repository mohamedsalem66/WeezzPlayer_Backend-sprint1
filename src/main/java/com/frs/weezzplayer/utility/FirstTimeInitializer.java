package com.frs.weezzplayer.utility;

import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.Role;
import com.frs.weezzplayer.model.ERole;
import com.frs.weezzplayer.repository.OrganizationRepository;
import com.frs.weezzplayer.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FirstTimeInitializer implements CommandLineRunner {

    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // TODO Auto-generated method stub
        if (roleRepository.findAll().isEmpty()) {
            Role UserRole = new Role( ERole.ROLE_USER);
            roleRepository.save(UserRole);
            Role AdminRole = new Role(ERole.ROLE_COMPANY);
            roleRepository.save(AdminRole);
             Role other = new Role(ERole.ROLE_MODERATOR);
            roleRepository.save(other);
        }


    }


}
