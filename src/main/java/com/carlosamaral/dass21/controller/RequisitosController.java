package com.carlosamaral.dass21.controller;

import com.carlosamaral.dass21.controller.crud.RespostaCrudController;
import com.carlosamaral.dass21.dto.RespostaDetailsDTO;
import com.carlosamaral.dass21.dto.SaveRespostaDTO;
import com.carlosamaral.dass21.dto.UpdateRespostaAndParticipanteDTO;
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
        return this.respostaCrudController.save(resposta);
    }

    @GetMapping("/e5")
    public ResponseEntity<List<RespostaDetailsDTO>> etapa5() {
        return this.respostaCrudController.findAll();
    }

    @PutMapping("/e6")
    public ResponseEntity<Optional<UpdateRespostaAndParticipanteDTO>> etapa6(@RequestBody UpdateRespostaAndParticipanteDTO req) {
        var res = requisitosService.updateRespostaAndParticipante(req);
        if (res.isEmpty()) return new ResponseEntity<>(Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/e7/{id}")
    public ResponseEntity<List<RespostaDetailsDTO>> etapa7(@PathVariable Long id) {
        return new ResponseEntity<>(respostaService.findByParticipanteId(id), HttpStatus.OK);
    }
}
