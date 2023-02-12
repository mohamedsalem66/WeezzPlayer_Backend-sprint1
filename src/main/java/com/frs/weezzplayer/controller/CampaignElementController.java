package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.service.CampaignElementService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/campaignElement")
@AllArgsConstructor
public class CampaignElementController {

    private final CampaignElementService campaignElementService;




}
