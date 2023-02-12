package com.frs.weezzplayer.repository.Reservation;

import com.frs.weezzplayer.entity.Reservation.BalanceAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceAccountRepository extends JpaRepository<BalanceAccount,Long> {
}
