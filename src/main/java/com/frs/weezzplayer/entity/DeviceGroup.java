package com.frs.weezzplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "device_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
//    @JsonBackReference
    private Channel channel;

    @OneToMany
//    @JsonManagedReference
    List<Device> device;


}
