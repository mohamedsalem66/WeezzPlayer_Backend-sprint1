package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.*;
import com.frs.weezzplayer.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/asset")
@AllArgsConstructor
public class AssetController {


    private final AssetService assetService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthDetailsService authDetailsService;
    private final FolderService folderService;
    
    /*@PostMapping("{id}")
    public ResponseEntity<Organization> addAssetToOrganization(@PathVariable Long id, @RequestParam("files") MultipartFile[] files)throws ChangeSetPersister.NotFoundException, IOException {
      Organization organization=organizationService.findOrganizationById(id);  
      List<Asset> assets=assetService.uploadFiles(files, organization);
      organization.getAssets().addAll(assets);
      organizationService.updateOrganization(organization);
      return new ResponseEntity<>(organization,HttpStatus.OK);
    	
    }
*/
    @PostMapping("{id}")
    public ResponseEntity<User> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles,@PathVariable Long id) throws IOException, ChangeSetPersister.NotFoundException {

        User user=userDetailsService.findUserById(id);
        List<Asset> assets = assetService.uploadFiles(multipartFiles,user);
        user.getAssets().addAll(assets);
        userDetailsService.updateUser(user);

        return ResponseEntity.ok().body(user);
    }
    @GetMapping("{id}")
    public ResponseEntity<List<Asset>> getAsset(@PathVariable Long id){
        return new ResponseEntity<>(authDetailsService.getUserById(id).getAssets(),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteAsset(@PathVariable Long id){
        assetService.deleteAssetById(id);
    }

   @PostMapping("/folder/{id}")
    public ResponseEntity<Folder> AddAssetToFolder(@RequestBody Asset asset,@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Folder folder=folderService.findFolderById(id);
        folder.getAssets().add(asset);
        folderService.UpdateFolder(folder);
        return new ResponseEntity<>(folder,HttpStatus.OK);



   }




    }



