package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.entity.Asset;
import com.frs.weezzplayer.entity.AssetConfig;
import com.frs.weezzplayer.entity.MicroProgram;
import com.frs.weezzplayer.model.AssetDTO;
import com.frs.weezzplayer.model.PlaylistDTO;
import com.frs.weezzplayer.service.AssetConfigService;
import com.frs.weezzplayer.service.MicroProgramService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/microprogram")
@AllArgsConstructor
public class MicroProgramController {


    private final MicroProgramService microProgramService;
    private final AssetConfigService assetConfigService;

    @PostMapping("")
    public ResponseEntity<MicroProgram> createMicroProgram(@RequestBody MicroProgram microProgram) {
        microProgram.setArchiveStatus(0);
        return new ResponseEntity<>(microProgramService.createMicroProgram(microProgram), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<MicroProgram>> getAllMicroProgram() {
        return new ResponseEntity<>(microProgramService.getAllMicroProgramNonArchived(), HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<MicroProgram>> getArchivedMicroProgram() {
        return new ResponseEntity<>(microProgramService.getAllMicroProgramArchived(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMicroProgram(@PathVariable Long id) {
        microProgramService.deleteMicroProgram(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MicroProgram> getMicroProgramById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
       MicroProgram microProgram=microProgramService.findById(id);
       return new ResponseEntity<>(microProgram,HttpStatus.OK);

    }

    @PostMapping("{id}")
    public ResponseEntity<MicroProgram> buildPlaylist(@RequestBody MicroProgram playlist, @PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        MicroProgram microprogram = microProgramService.findById(id);
        for (Asset asset:playlist.getAssets()){
            microprogram.getAssets().add(asset);

        }
        microProgramService.updateMicroProgram(microprogram);





        return new ResponseEntity<>(microprogram,HttpStatus.OK);
    }



}







