package com.frs.weezzplayer.model.Config;

import lombok.Data;

@Data
public class LongPollingConfig {
    private Long timeOut = 3600000L;
    private String response = "Time Out";
}
