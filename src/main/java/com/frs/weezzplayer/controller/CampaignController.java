package com.frs.weezzplayer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frs.weezzplayer.entity.Campaign;
import com.frs.weezzplayer.entity.CampaignElement;
import com.frs.weezzplayer.model.AssetOfCampaign;
import com.frs.weezzplayer.model.AssetsOfCampaignElement;
import com.frs.weezzplayer.service.CampaignElementService;
import com.frs.weezzplayer.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/campaign")
@AllArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;
    private final CampaignElementService campaignElementService;


    @GetMapping("{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id ) throws ChangeSetPersister.NotFoundException {
        return  new ResponseEntity<>(campaignService.findCampaignById(id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCampaignById(@PathVariable Long id){
        campaignService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<Campaign>> getAllCampaigns(){
        return new ResponseEntity<>(campaignService.getAllCampaigns(),HttpStatus.OK);
    }
    @GetMapping("active")
    public ResponseEntity<List<Campaign>> getActiveCampaigns(){
        return new ResponseEntity<>(campaignService.getCampaignsByIsActive(true),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Campaign> addCampaign(@Valid @RequestBody Campaign campaign){
        return  new ResponseEntity<>(campaignService.createCampaign(campaign),HttpStatus.OK);
    }

    @PostMapping("element/{id}")
    public ResponseEntity<Campaign> addCampaignElement(@PathVariable Long id,
                                                       @RequestBody
                                                               String campaignelements) throws ChangeSetPersister.NotFoundException, JsonProcessingException {


        Campaign campaign = campaignService.findCampaignById(id);

        campaign.setConfig(campaignelements);

        campaign = campaignService.updateCampaign(campaign);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    };
}
