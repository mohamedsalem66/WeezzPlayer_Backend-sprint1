package com.frs.weezzplayer.model;

import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.service.UserDetailsImpl;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String jwtToken;
    private Long id ;

}
