package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT u FROM Reservation u WHERE u.DateReserve = ?1")
    List<Reservation> getReservationByDate(Date date) ;
}
