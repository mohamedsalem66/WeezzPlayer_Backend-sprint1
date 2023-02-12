package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Device;
import com.frs.weezzplayer.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/device")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("")
    public ResponseEntity<List<Device>> getAllDevices() {
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }

    @GetMapping("{device}")
    public ResponseEntity<Device> getDeviceByIdentifier(@PathVariable String device) {
        return new ResponseEntity<>(deviceService.getDeviceByIdentifier(device), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.createDevice(device), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Device> updateDevice(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.updateDevice(device), HttpStatus.OK);
    }

}
