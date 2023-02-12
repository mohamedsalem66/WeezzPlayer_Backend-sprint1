package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDeviceByIdentifier(String identifier);
}
