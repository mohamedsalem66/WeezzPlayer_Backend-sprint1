package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Reservation.Reservation;
import com.frs.weezzplayer.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation NewReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }
    public List<Reservation> getReservationPerHours(Date date){
        return reservationRepository.getReservationByDate(date);
    }
    public List<Reservation> getAlReservation(){
        return reservationRepository.findAll();
    }
}
