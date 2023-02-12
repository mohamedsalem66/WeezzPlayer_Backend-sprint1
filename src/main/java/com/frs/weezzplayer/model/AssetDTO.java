package com.frs.weezzplayer.model;
import com.frs.weezzplayer.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;


@Data
public class AssetDTO {
    private String name;
    @Column(unique = true)
    private String url;
    private Long size;
    private String type;
    @Column(columnDefinition = "json")
    private String config;
    



}
