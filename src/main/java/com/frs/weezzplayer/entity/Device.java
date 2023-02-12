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
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String identifier;
    private String size;
    private String modele;
    private String marque;
    private String aspetRatio;
    private String portconnexions;

    @OneToMany
   // @JsonManagedReference(value = "device_screenDevice")
    private List<DeviceScreen> deviceScreen;

    @ManyToOne
    Campaign campaign;
}
