package com.frs.weezzplayer.service;
import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.Folder;
import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.repository.FolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public Folder Createfolder(Folder folder){

        return  folderRepository.save(folder);
    }
    public Folder UpdateFolder(Folder folder) {

        return folderRepository.save(folder);
    }
    public Folder findFolderById(Long id)  throws ChangeSetPersister.NotFoundException {
        return folderRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
    public List<Folder> GetAllFolder(){
       List<Folder> folders= folderRepository.findAll();
       return folders;
    }
   public void delete(Long id){
        folderRepository.deleteById(id);
    }/*
    public List<Asset> getAssetByFolderName(String name){
        Folder folder=folderRepository.findByName(name);

    }*/
}
