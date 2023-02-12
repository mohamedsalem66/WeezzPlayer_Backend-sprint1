package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.Site;
import com.frs.weezzplayer.repository.OrganizationRepository;
import com.frs.weezzplayer.repository.SiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;
    private final OrganizationRepository organizationRepository;
    public Site createSite(Site site) {
        return this.siteRepository.save(site);
    }


    public List<Site> getAllSite() {
        return siteRepository.findAll();
    }

    public Site updateSite(Site site) {
        Site SiteUpdate = siteRepository.findById(site.getId()).get();

        SiteUpdate.setName(site.getName());
        SiteUpdate.setLocation(site.getLocation());
        return siteRepository.save(SiteUpdate);
    }

    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
    
    


}
