package com.frs.weezzplayer.repository;

import com.frs.weezzplayer.entity.MicroProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroProgramRepository extends JpaRepository<MicroProgram, Long> {

    List<MicroProgram> findByArchiveStatus(Integer ArchiveStatus);
     MicroProgram findByName(String name);
}
