package com.frs.weezzplayer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class MicroProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long duration;
    private Integer archiveStatus;

    @CreationTimestamp
    private Instant deleteAt;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    private String createdBy;
    private String updatedBy;

    //@JsonManagedReference(value = "microProgram_app")
    @OneToMany()
    private List<App> apps;
    @ManyToMany()
    private List<Asset> assets;


    @OneToMany()
    //@JsonManagedReference(value = "microProgram_asset_config")
    private List<AssetConfig> assetConfigs;

}
