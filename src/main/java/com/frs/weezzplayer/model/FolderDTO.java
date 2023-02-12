package com.frs.weezzplayer.model;

import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.Folder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

public class FolderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @CreationTimestamp
    private Instant CreatedAt;
    @OneToMany
    private List<Asset> assets;
    private List<Folder> subfolder;
}
