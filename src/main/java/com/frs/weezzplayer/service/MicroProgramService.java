package com.frs.weezzplayer.service;


import com.frs.weezzplayer.entity.MicroProgram;
import com.frs.weezzplayer.repository.MicroProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class MicroProgramService {


    private final MicroProgramRepository microProgramRepository;

    public MicroProgram createMicroProgram(MicroProgram microProgram) {

        return microProgramRepository.save(microProgram);
    }
    public MicroProgram updateMicroProgram(MicroProgram microprogram){
        return microProgramRepository.save(microprogram);
    }
    public MicroProgram findById(Long id) throws ChangeSetPersister.NotFoundException {
        return microProgramRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<MicroProgram> getAllMicroProgramNonArchived() {

        return microProgramRepository.findByArchiveStatus(0);
    }

    public List<MicroProgram> getAllMicroProgramArchived() {

        return microProgramRepository.findByArchiveStatus(1);
    }

public List<MicroProgram> getAllMicroProgram(){
        return microProgramRepository.findAll();
}
    public void deleteMicroProgram(Long id) {
        MicroProgram microProgram = microProgramRepository.findById(id).get();
        microProgram.setArchiveStatus(1);
        microProgram.setDeleteAt(Instant.now());
        microProgramRepository.save(microProgram);
    }


}
