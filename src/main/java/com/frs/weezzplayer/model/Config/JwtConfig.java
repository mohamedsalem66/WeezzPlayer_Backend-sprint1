package com.frs.weezzplayer.model.Config;

import lombok.Data;

@Data
public class JwtConfig {
    private String secretKey="securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
    private String tokenPrefix="Bearer ";
    private int tokenExpireIn= 360000000;

}
