package com.carlosamaral.dass21.controller.crud;

import com.carlosamaral.dass21.model.ParticipanteModel;
import com.carlosamaral.dass21.service.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/crud/participante")
public class ParticipanteCrudController {
    private final ParticipanteService participanteService;
    public ParticipanteCrudController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<ParticipanteModel>> findAll() {
        return new ResponseEntity<>(this.participanteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<ParticipanteModel>> findById(@PathVariable Long id) {
        if (id <= 0) return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        Optional<ParticipanteModel> participanteFound = this.participanteService.findById(id);
        return new ResponseEntity<>(participanteFound, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Optional<ParticipanteModel>> save(@RequestBody ParticipanteModel participanteModel) {
        if (!participanteService.participanteIsValid(participanteModel, false)) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

        ParticipanteModel participanteModelSavedObject = this.participanteService.save(participanteModel);
        return new ResponseEntity<>(Optional.of(participanteModelSavedObject), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ParticipanteModel> update(@RequestBody ParticipanteModel participanteModel) {
        if (!participanteService.participanteIsValid(participanteModel, true)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ParticipanteModel participanteModelSavedObject = this.participanteService.update(participanteModel);
        return new ResponseEntity<>(participanteModelSavedObject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            this.participanteService.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
