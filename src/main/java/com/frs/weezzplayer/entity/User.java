package com.frs.weezzplayer.entity;

import com.frs.weezzplayer.entity.Reservation.BalanceAccount;
import com.frs.weezzplayer.entity.Reservation.Reservation;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstname;
    private String lastname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;


    private String password;
    private Boolean isEnabled;


    @OneToMany
    private List<Asset> assets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )

    private Set<Role> roles = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private BalanceAccount account;
    @OneToOne(cascade = CascadeType.ALL)
    private Organization organization;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_user_id")
    private Collection<Reservation> reservations;





}
