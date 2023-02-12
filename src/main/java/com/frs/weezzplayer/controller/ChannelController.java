package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Channel;
import com.frs.weezzplayer.entity.Device;
import com.frs.weezzplayer.entity.DeviceGroup;
import com.frs.weezzplayer.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/channel")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping("")
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {

        return new ResponseEntity<>(channelService.createChannel(channel), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Channel>> getAllChannel() {

        return new ResponseEntity<>(channelService.getAllChannel(), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteChannelById(@PathVariable Long id){
        channelService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    @PostMapping("{id}")
    public ResponseEntity<Channel> addDeviceGroupToChannel(@PathVariable Long id,
                                                              @RequestBody DeviceGroup devicegroup) throws ChangeSetPersister.NotFoundException {
        Channel channel = channelService.findChannelById(id);
        channel.getDeviceGroup().add(devicegroup);
        channel = channelService.updateChannel(channel);
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }


}
