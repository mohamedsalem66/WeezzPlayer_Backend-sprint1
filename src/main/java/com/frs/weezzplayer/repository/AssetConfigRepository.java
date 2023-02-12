package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.AssetConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetConfigRepository extends JpaRepository<AssetConfig,Long> {

}
