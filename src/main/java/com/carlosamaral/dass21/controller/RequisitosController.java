package com.carlosamaral.dass21.controller;

import com.carlosamaral.dass21.controller.crud.RespostaCrudController;
import com.carlosamaral.dass21.dto.RespostaDetailsDTO;
import com.carlosamaral.dass21.dto.SaveRespostaDTO;
import com.carlosamaral.dass21.dto.UpdateRespostaAndParticipanteDTO;
import com.carlosamaral.dass21.model.RespostaModel;
import com.carlosamaral.dass21.service.RequisitosService;
import com.carlosamaral.dass21.service.RespostaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/requisitos")
public class RequisitosController {
    private final RespostaCrudController respostaCrudController;
    private final RespostaService respostaService;

    private final RequisitosService requisitosService;

    public RequisitosController(RespostaCrudController respostaCrudController, RespostaService respostaService, RequisitosService requisitosService) {
        this.respostaCrudController = respostaCrudController;
        this.respostaService = respostaService;
        this.requisitosService = requisitosService;
    }

    @PostMapping("/e4")
    public ResponseEntity<Optional<RespostaDetailsDTO>> etapa4(@RequestBody SaveRespostaDTO resposta) {
        try {
            if (!respostaService.respostaIsValid(resposta)) {
                return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
            }

            RespostaModel respostaModel = this.respostaService.save(resposta);
            RespostaDetailsDTO response = respostaService.toDetailsDTO(respostaModel);
            return new ResponseEntity<>(Optional.of(response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/e5")
    public ResponseEntity<List<RespostaDetailsDTO>> etapa5() {
        return this.respostaCrudController.findAll();
    }

    @PutMapping("/e6")
    public ResponseEntity<Optional<UpdateRespostaAndParticipanteDTO>> etapa6(@RequestBody UpdateRespostaAndParticipanteDTO req) {
        try {
            var res = requisitosService.updateRespostaAndParticipante(req);
            if (res.isEmpty()) return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/e7/{id}")
    public ResponseEntity<List<RespostaDetailsDTO>> etapa7(@PathVariable Long id) {
        try {
            var res = respostaService.findByParticipanteId(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }
    }
}
