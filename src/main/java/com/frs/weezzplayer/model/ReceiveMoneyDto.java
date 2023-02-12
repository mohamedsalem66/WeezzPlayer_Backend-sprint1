package com.frs.weezzplayer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiveMoneyDto {
    private  String email;
    private BigDecimal balance;
}
