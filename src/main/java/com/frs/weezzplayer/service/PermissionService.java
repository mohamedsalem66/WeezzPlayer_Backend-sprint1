package com.frs.weezzplayer.service;

import com.frs.weezzplayer.entity.Permission;
import com.frs.weezzplayer.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);

    }


}
