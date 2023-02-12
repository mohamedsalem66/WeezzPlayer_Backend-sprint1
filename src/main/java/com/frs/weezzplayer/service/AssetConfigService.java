package com.frs.weezzplayer.service;
import java.util.List;

import com.frs.weezzplayer.entity.AssetConfig;
import com.frs.weezzplayer.repository.AssetConfigRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AssetConfigService {
    private final AssetConfigRepository assetConfigRepository;
    public AssetConfig createAssetConfig(AssetConfig assetConfig){
        return assetConfigRepository.save(assetConfig);
    }


}
