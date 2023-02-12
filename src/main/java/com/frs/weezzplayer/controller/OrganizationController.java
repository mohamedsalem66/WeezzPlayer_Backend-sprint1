package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/organization")
@AllArgsConstructor
public class OrganizationController {


    private final OrganizationService organizationService;
    
    


    @PostMapping("")
    public ResponseEntity<Organization> createOrganization(@RequestBody @Valid Organization organization) {
        return new ResponseEntity<>(organizationService.createOrganization(organization), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Organization>> getAllOrganization() {
        return new ResponseEntity<>(organizationService.getAllOrganization(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Organization> updateOrganization(@RequestBody @Valid Organization organization) {
        return new ResponseEntity<>(organizationService.updateOrganization(organization), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }

}
