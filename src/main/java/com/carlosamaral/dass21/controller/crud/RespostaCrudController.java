package com.carlosamaral.dass21.controller.crud;

import com.carlosamaral.dass21.dto.RespostaDetailsDTO;
import com.carlosamaral.dass21.dto.SaveRespostaDTO;
import com.carlosamaral.dass21.dto.UpdateRespostaDTO;
import com.carlosamaral.dass21.model.RespostaModel;
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
@RequestMapping("/crud/resposta")
public class RespostaCrudController {
    private final RespostaService respostaService;
    public RespostaCrudController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<RespostaDetailsDTO>> findAll() {
        return new ResponseEntity<>(this.respostaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<RespostaDetailsDTO>> findById(@PathVariable Long id) {
        if (id <= 0) return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);

        Optional<RespostaDetailsDTO> respostaFound = this.respostaService.findById(id);
        return new ResponseEntity<>(respostaFound, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Optional<RespostaDetailsDTO>> save(@RequestBody SaveRespostaDTO resposta) {
        if (!respostaService.respostaIsValid(resposta)) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

        RespostaModel respostaModel = this.respostaService.save(resposta);
        RespostaDetailsDTO response = respostaService.toDetailsDTO(respostaModel);
        return new ResponseEntity<>(Optional.of(response), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<RespostaDetailsDTO>> update(@RequestBody UpdateRespostaDTO body) {
        try {
            var res = this.respostaService.update(body);
            return new ResponseEntity<>(Optional.of(res), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            if (respostaService.findById(id).isEmpty()) {
                return new ResponseEntity<>("Resposta not found", HttpStatus.BAD_REQUEST);
            }

            respostaService.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
