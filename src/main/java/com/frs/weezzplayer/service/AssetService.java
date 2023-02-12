package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.model.Config.StorageConfig;
import com.frs.weezzplayer.repository.AssetRepository;

import java.awt.image.BufferedImage;


import java.nio.file.Files;

import lombok.AllArgsConstructor;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.constraints.Null;

@Service
@AllArgsConstructor
public class AssetService {
	
    
    private final AssetRepository assetRepository;
    private final OrganizationService organizationService;

    private final StorageConfig Directory;
    
    public List<Asset> uploadFiles(List<MultipartFile> files,  User user) throws IOException, ChangeSetPersister.NotFoundException {
    	
    	List<Asset> assets=new ArrayList<>();
    	for(MultipartFile file:files){
    	    if(file.getSize()>0){
    	        assets.add(uploadToLocalFileSystem(file,user));
            }
        }
        return assets;
        
    }
        public Asset uploadToLocalFileSystem(MultipartFile file,User user ) throws ChangeSetPersister.NotFoundException {
 

    	String fileName =file.getOriginalFilename();
        Path storageDirectory = Paths.get(Directory.getDirectory());
        VerifyStoragePath(storageDirectory);
        Path fileStorage=  Paths.get(String.valueOf(storageDirectory),fileName).toAbsolutePath().normalize();

        try {
            Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

            Asset asset=new Asset(null,fileName,file.getContentType(),fileURI(fileName),file.getSize(),0,Instant.now(),null,null,null,false,null,null,null,null);

            assetRepository.save(asset);


            return asset;
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
    private String fileURI(String fileName) {
        return Directory.getUrl() + fileName;
    }

    private String GenerateUniqueFileName(String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        long millis = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        return uuid + '_' + millis + '.' + extension;
    }

    public List<Asset> getAllasset(){
       return  assetRepository.findAll();
}

  /*public List<Asset> getAllAssetNonArchived(){
    //    return assetRepository.findByArchivedStatus(0);
  }
  public List<Asset> getAllAssetArchived(){
       // return assetRepository.findByArchivedStatus(1);
    }*/
public void deleteAssetById(Long id){
      // asset.setArchivedStatus(1);
       assetRepository.deleteById(id);
}
}
