package com.frs.weezzplayer.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "device_screen")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceScreen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    //@JsonBackReference(value = "device_screenDevice")
    private Device device;

    @ManyToOne
    private Screen screen;
}
