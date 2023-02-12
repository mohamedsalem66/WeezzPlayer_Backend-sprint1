package com.frs.weezzplayer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
//    @JsonManagedReference
    private List<DeviceGroup> deviceGroup;

    @OneToMany()
//    @JsonManagedReference
    private List<Campaign> campaign;


}
