package com.frs.weezzplayer.model;

import com.frs.weezzplayer.entity.Campaign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceUpdate {
    private String identifier;
    private Campaign campaign;
}
