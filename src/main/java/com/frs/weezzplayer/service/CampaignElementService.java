package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Campaign;
import com.frs.weezzplayer.entity.CampaignElement;
import com.frs.weezzplayer.model.Config.StorageConfig;
import com.frs.weezzplayer.repository.CampaignElementRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CampaignElementService {

    private final CampaignElementRepository campaignElementRepository;
    private final StorageConfig storageConfig;

    public CampaignElement createCampaignElement(CampaignElement campaignElement) {
        return campaignElementRepository.save(campaignElement);
    }

/*
    public List<CampaignElement> uploadFiles(MultipartFile[] files, Campaign campaign) {
        List<CampaignElement> campaignElements = new ArrayList<>();
        Arrays.asList(files).forEach(file -> {
            if (file.getSize() > 0) {
                campaignElements.add(uploadToLocalFileSystem(file, campaign));
            }
        });
        return campaignElements;
    }

   public CampaignElement uploadToLocalFileSystem(MultipartFile file, Campaign campaign) {

        String fileName = GenerateUniqueFileName(file.getOriginalFilename());
        Path storageDirectory = Paths.get(storageConfig.getDirectory());
        VerifyStoragePath(storageDirectory);
        Path destination = Paths.get(storageConfig.getDirectory() + fileName);
        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CampaignElement campaignElement = new CampaignElement(null,
                fileURI(fileName), false, Instant.now(), null, campaign);
        return campaignElementRepository.save(campaignElement);
    }*/


    private String fileURI(String fileName) {
        return storageConfig.getUrl() + fileName;
    }


    private void VerifyStoragePath(Path storageDirectory) {
        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String GenerateUniqueFileName(String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        long millis = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        return uuid + '_' + millis + '.' + extension;
    }


}
