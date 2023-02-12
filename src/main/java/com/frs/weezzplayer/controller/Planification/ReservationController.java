package com.frs.weezzplayer.controller.Planification;
import com.frs.weezzplayer.entity.Reservation.Reservation;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.exception.BadDateFormatException;
import com.frs.weezzplayer.service.AuthDetailsService;
import com.frs.weezzplayer.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {

    private final AuthDetailsService authDetailsService;

    private final ReservationService reservationService;
    @PostMapping("{id}")
    public ResponseEntity<User> addReservation(@Valid @RequestBody Reservation reservation,@PathVariable Long id) throws ParseException {




       Reservation res= reservationService.NewReservation(reservation);
        User user= authDetailsService.getUserById(id);
        user.getReservations().add(res);

        BigDecimal currentAmount=user.getAccount().getBalance();
       BigDecimal ModifyAmount=currentAmount.subtract(reservation.getPrice());
       user.getAccount().setBalance(ModifyAmount);
        authDetailsService.updateUser(user);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("{date}")
    public ResponseEntity<List<Reservation>> getAllReservation(@PathVariable String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final long reqHoursInMillis = 1 * 60 * 60 * 1000;  // change 1 with required hour

        Instant findate = format.parse(date).toInstant().plusMillis(3600000);

        return new ResponseEntity<>(reservationService.getReservationPerHours(Date.from(findate)),HttpStatus.OK);
    }
    @GetMapping("/ByUserId/{id}")
    public ResponseEntity<Collection<Reservation>> getReservationById(@PathVariable Long id)  {
        User user= authDetailsService.getUserById(id);

        return new ResponseEntity<>(user.getReservations(),HttpStatus.OK);
    }

}
