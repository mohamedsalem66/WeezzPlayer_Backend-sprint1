package com.frs.weezzplayer.service.BlanceAccountOperation;

import com.frs.weezzplayer.entity.Reservation.BalanceAccount;
import com.frs.weezzplayer.repository.Reservation.BalanceAccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BalanceAccountService {
    private final BalanceAccountRepository balanceAccountRepository;



}
