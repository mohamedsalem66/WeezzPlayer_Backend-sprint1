package com.frs.weezzplayer.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetsOfCampaignElement {

    private Long id;
    private Long group;
    private Long order;
    private Long duration;
    private String content;
    private String title;
    private String type;
    private String style;
    private String src;
    private String start ;
    private String end;
}


