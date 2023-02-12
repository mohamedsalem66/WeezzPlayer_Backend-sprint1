package com.frs.weezzplayer.controller.Planification;

import com.frs.weezzplayer.entity.Reservation.Event;
import com.frs.weezzplayer.exception.BadDateFormatException;
import com.frs.weezzplayer.repository.Reservation.EventRepository;
import com.frs.weezzplayer.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventRepository eventRepository;
    @PostMapping("/event")
    public ResponseEntity<Event> addEvent(@Valid @RequestBody Event event){
      Event newEvent = eventService.createEvent(event)  ;
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }
    @PostMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@Valid @RequestBody String title, @PathVariable Long id) {
        return new ResponseEntity<>(eventService.updateEvent(id,title),HttpStatus.OK);
    }
    @RequestMapping(value ="/event",method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEvents(){

        return new ResponseEntity<>(eventService.GetAllEvents(),HttpStatus.OK);

    }
    @RequestMapping(value = "/event/{id}",method = RequestMethod.DELETE)
    public void DeleteEventById(@PathVariable Long id ){
        eventService.removeEvent(id);
    }
    @RequestMapping(value="/events", method=RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsInRange(@PathParam(value = "start") String start,
                                        @PathParam(value = "end") String end) {
       Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());

        return new ResponseEntity<>(eventRepository.findByDateBetween(startDateTime, endDateTime),HttpStatus.OK);
    }


}
