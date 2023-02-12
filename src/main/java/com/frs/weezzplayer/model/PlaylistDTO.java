package com.frs.weezzplayer.model;
import com.frs.weezzplayer.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
public class PlaylistDTO {
    private String name;
    private Long duration;

    private List<AssetDTO> assets;
}
