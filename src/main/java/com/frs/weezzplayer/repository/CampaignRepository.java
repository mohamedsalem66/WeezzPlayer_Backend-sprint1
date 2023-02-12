package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findCampaignsByIsActive(Boolean isActive);
}
