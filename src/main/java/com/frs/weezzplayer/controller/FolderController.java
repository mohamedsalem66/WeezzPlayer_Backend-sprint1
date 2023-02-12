package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.Folder;
import com.frs.weezzplayer.service.AssetService;
import com.frs.weezzplayer.service.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/folder")
@AllArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping
    public Folder createFolder(@RequestBody Folder folder){

       return folderService.Createfolder(folder);
    }


    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolder(){
        return new ResponseEntity<>(folderService.GetAllFolder(),HttpStatus.OK);

    }
   @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFolderById(@PathVariable Long id){
        folderService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    @GetMapping("/name/{id}")
    public ResponseEntity<Folder> getNameByFolderId(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Folder folder=folderService.findFolderById(id);
        return new ResponseEntity<>(folder,HttpStatus.OK);
    }
    /*
    @GetMapping("{name}")
    public List<Asset> GetAssetByFolderName(@PathVariable String name){
      List<Asset> assets= folderService.getAssetByFolderName(name);
      return assets;
    }*/
}
