package com.frs.weezzplayer.service;
import com.frs.weezzplayer.repository.OrganizationRepository;
import com.frs.weezzplayer.entity.Organization;
import lombok.AllArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization createOrganization(Organization organization) {

        return organizationRepository.save(organization);
    }
    
    public Organization UpdateOrganization(Organization organization) {

        return organizationRepository.save(organization);
    }

    public List<Organization> getAllOrganization() {
        return organizationRepository.findAll();

    }

    public Organization updateOrganization(Organization organization) {

        Organization organizationUpdate = organizationRepository.findById(organization.getId()).get();

        organizationUpdate.setName(organization.getName());
        organizationUpdate.setLocation(organization.getLocation());
        organizationUpdate.setIndustry(organization.getLocation());

        return organizationRepository.save(organizationUpdate);
    }
    
    public Organization findOrganizationById(Long id)  throws ChangeSetPersister.NotFoundException {
    	return organizationRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
    
  
    


}
