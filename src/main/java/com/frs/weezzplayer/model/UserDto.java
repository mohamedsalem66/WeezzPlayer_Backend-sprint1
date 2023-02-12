package com.frs.weezzplayer.model;

import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.Reservation.BalanceAccount;
import com.frs.weezzplayer.entity.Reservation.Reservation;
import com.frs.weezzplayer.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private List<Asset> assets;
    private Set<Role> role;
    private BalanceAccount balance;
    private Collection<Reservation> reservation;

}
