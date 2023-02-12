package com.frs.weezzplayer.service;


import com.frs.weezzplayer.entity.Campaign;
import com.frs.weezzplayer.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;



    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }
    public Campaign findCampaignById(Long id) throws ChangeSetPersister.NotFoundException {

        return campaignRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        // ADD NotFoundException
    }

    public List<Campaign> getAllCampaigns() {
        return  campaignRepository.findAll();
    }


    public Campaign updateCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getCampaignsByIsActive(Boolean isActive) {
        return campaignRepository.findCampaignsByIsActive(isActive);
    }

    public void delete(Long id) {
        campaignRepository.deleteById(id);
    }
}
