package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Organization;
import com.frs.weezzplayer.entity.Site;
import com.frs.weezzplayer.entity.User;
import com.frs.weezzplayer.exception.InvalidTokenException;
import com.frs.weezzplayer.model.ReceiveMoneyDto;
import com.frs.weezzplayer.model.UserDto;
import com.frs.weezzplayer.service.AuthDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final AuthDetailsService authDetailsService;

    @GetMapping
        public ResponseEntity<List<User>> GetAllUsers() {
        return new ResponseEntity<>(authDetailsService.getAllUser(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        User user=authDetailsService.getUserById(id);
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRoles());
        userDto.setAssets(user.getAssets());
        userDto.setBalance(user.getAccount());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setReservation(user.getReservations());
 return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/money")
    public ResponseEntity<?> sendMoneyToUser( @RequestBody ReceiveMoneyDto receiveMoneyDto   ) {
        User user=authDetailsService.getByEmail(receiveMoneyDto.getEmail());
        BigDecimal actualBalace=user.getAccount().getBalance();
        BigDecimal newBalance=actualBalace.add(receiveMoneyDto.getBalance());
        user.getAccount().setBalance(newBalance);
        authDetailsService.updateUser(user);
        Map<String,Object> response=new HashMap<>();
        response.put("email",user.getEmail());
        response.put("new Balance",user.getAccount().getBalance());
        return new ResponseEntity<>(response,HttpStatus.OK) ;

    }


}
