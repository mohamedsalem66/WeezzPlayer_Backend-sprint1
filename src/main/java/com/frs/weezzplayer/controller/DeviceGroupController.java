package com.frs.weezzplayer.controller;
import com.frs.weezzplayer.entity.Device;
import com.frs.weezzplayer.entity.DeviceGroup;
import com.frs.weezzplayer.service.DeviceGroupService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/devicegroup")
@AllArgsConstructor
public class DeviceGroupController {
    private final DeviceGroupService deviceGroupService;
    @PostMapping("")
    public ResponseEntity<DeviceGroup> createDeviceGroup(@Valid @RequestBody DeviceGroup devicegroup) {
        return new ResponseEntity<>(deviceGroupService.createDeviceGroup(devicegroup), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DeviceGroup>> getAllDevicesGroup() {
        return new ResponseEntity<>(deviceGroupService.getAllDevicesGroup(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DeviceGroup> getDevicesGroupById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(deviceGroupService.findDeviceGroupById(id), HttpStatus.OK);
    }
    @PostMapping("{id}")
    public ResponseEntity<DeviceGroup> addScreenToDeviceGroup(@PathVariable Long id,
                                                       @RequestBody Device device) throws ChangeSetPersister.NotFoundException {
        DeviceGroup devicegroup = deviceGroupService.findDeviceGroupById(id);
        devicegroup.getDevice().add(device);
        devicegroup = deviceGroupService.updateDeviceGroup(devicegroup);
        return new ResponseEntity<>(devicegroup, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGroupDeviceById(@PathVariable Long id){
        deviceGroupService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }


}
