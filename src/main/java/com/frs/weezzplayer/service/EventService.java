package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Reservation.Event;
import com.frs.weezzplayer.repository.Reservation.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    public Event updateEvent( Long id, String title) {
        Event event=eventRepository.getById(id);
        event.setTitle(title);
       return eventRepository.save(event);
    }
    public void removeEvent( Long  id) {
        eventRepository.deleteById(id);
    }
    public List<Event> GetAllEvents() {
        return eventRepository.findAll();
    }





}
