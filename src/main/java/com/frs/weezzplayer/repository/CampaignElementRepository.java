package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.CampaignElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignElementRepository extends JpaRepository<CampaignElement, Long> {
}
