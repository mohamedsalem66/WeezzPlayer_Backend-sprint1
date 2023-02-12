package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Device;
import com.frs.weezzplayer.model.BlockingQueue;
import com.frs.weezzplayer.model.DeviceUpdate;
import com.frs.weezzplayer.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final BlockingQueue<DeviceUpdate> queue;

    public Device getDeviceById(Long id) throws ChangeSetPersister.NotFoundException {
        return deviceRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device updateDevice(Device device) {
        Device updateDevice = deviceRepository.save(device);
        fireUpdate(updateDevice);
        return updateDevice;
    }

    public Device getDeviceByIdentifier(String device) {
        return deviceRepository.findDeviceByIdentifier(device);
    }

    public void fireUpdate(Device device) {
        queue.add(new DeviceUpdate(device.getIdentifier(), device.getCampaign()));
    }
}
