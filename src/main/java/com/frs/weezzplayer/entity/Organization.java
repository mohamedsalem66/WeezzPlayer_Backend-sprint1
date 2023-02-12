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
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String industry;
    public Organization(String name, String location,String industry)

     {
        this.name=name;
        this.location=location;
        this.industry=industry;
    }

    @OneToMany()
    private List<Site> sites;

    @OneToMany()
    private List<Asset> assets;

    @OneToMany
    private List<RoleUserOrganization> roleUserOrganizations;


}
