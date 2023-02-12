package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Campaign;
import com.frs.weezzplayer.entity.Device;
import com.frs.weezzplayer.entity.DeviceGroup;
import com.frs.weezzplayer.repository.DeviceGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceGroupService {
    private final DeviceGroupRepository  deviceGroupRepository;
    public DeviceGroup createDeviceGroup(DeviceGroup deviceGroup){
        return deviceGroupRepository.save(deviceGroup);
    }
    public List<DeviceGroup> getAllDevicesGroup() {
        return deviceGroupRepository.findAll();
    }
    public DeviceGroup findDeviceGroupById(Long id) throws ChangeSetPersister.NotFoundException {

        return deviceGroupRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        // ADD NotFoundException
    }
    public DeviceGroup updateDeviceGroup(DeviceGroup deviceGroup) {
        return deviceGroupRepository.save(deviceGroup);
    }


    public void delete(Long id) {
        deviceGroupRepository.deleteById(id);

    }
}

