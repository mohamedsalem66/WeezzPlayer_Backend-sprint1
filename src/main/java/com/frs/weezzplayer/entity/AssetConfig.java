package com.frs.weezzplayer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "json")
    private String config;
    private String order;

    @ManyToOne
    //@JsonBackReference(value ="asset_assetConfig" )
    private Asset asset;

    @ManyToOne
    //@JsonBackReference(value = "microProgram_asset_config")
    private MicroProgram microProgram;

}
