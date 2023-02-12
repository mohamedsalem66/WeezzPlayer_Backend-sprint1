package com.frs.weezzplayer.entity;

import com.frs.weezzplayer.model.ERole;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter()
@Getter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;



    public Role(ERole name) {
        this.name = name;
    }

    @OneToMany()
    //@JsonManagedReference(value ="role_permission")
    private List<Permission> permissions;



}
