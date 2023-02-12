package com.frs.weezzplayer.repository.Reservation;

import com.frs.weezzplayer.entity.Reservation.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e where e.startTime >= ?1 and e.endTime <= ?2")
    public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
    @Modifying
    @Query("update Event e set e.title = ?1 where e.id = ?2")
    Event setEventInfoById(String title, Long id);
}
