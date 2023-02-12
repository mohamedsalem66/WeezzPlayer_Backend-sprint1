package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.MicroProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
 //List<Asset> findByArchivedStatus(Integer ArchiveStatus);

}
