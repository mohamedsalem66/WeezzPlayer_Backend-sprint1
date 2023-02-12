package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.Site;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.service.OrganizationService;
import com.frs.weezzplayer.service.SiteService;
import lombok.AllArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/site")
@AllArgsConstructor
public class SiteController {


    private final SiteService siteService;
    private final OrganizationService organizationService;
    
    @PostMapping(value = "{id}")
    public ResponseEntity<Organization> CreateSiteIntoOrg(@PathVariable Long id, @RequestBody Site site  ) throws ChangeSetPersister.NotFoundException{
    	Organization organization=organizationService.findOrganizationById(id);
    	site = siteService.createSite(site);
    	organization.getSites().add(site);
    	organizationService.updateOrganization(organization);
    	return new ResponseEntity<>(organization,HttpStatus.OK);
    	
    	}
    
    @PostMapping("")
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        return new ResponseEntity<>(siteService.createSite(site), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Site>> getAllSite() {
        return new ResponseEntity<>(siteService.getAllSite(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Site> updateSite(@RequestBody Site site) {
        return new ResponseEntity<>(siteService.updateSite(site), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteOrganization(@PathVariable Long id) {
        siteService.deleteSite(id);
    }


}
