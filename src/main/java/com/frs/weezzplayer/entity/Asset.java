package com.frs.weezzplayer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    @Column(unique = true)
    private String url;

    private Long size;

    private Integer ArchivedStatus;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    private String createdBy;
    private String updatedBy;
    private Boolean isValid;


    @ManyToOne
    @JsonIgnore
    //@JsonBackReference(value = "organization_asset")
    private User user;

    @OneToMany
    //@JsonManagedReference(value ="asset_assetConfig" )
    private List<AssetConfig> assetConfigs;
    @ManyToOne
    private Folder folder;
    @ManyToMany
    private List<MicroProgram> microPrograms;


}


