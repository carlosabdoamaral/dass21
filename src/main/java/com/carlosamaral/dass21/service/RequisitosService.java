package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.dto.RespostaDetailsDTO;
import com.carlosamaral.dass21.dto.RespostaMinDetailsDTO;
import com.carlosamaral.dass21.dto.UpdateRespostaAndParticipanteDTO;
import com.carlosamaral.dass21.dto.UpdateRespostaDTO;
import com.carlosamaral.dass21.model.ParticipanteModel;
import com.carlosamaral.dass21.model.RespostaModel;
import com.carlosamaral.dass21.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RequisitosService {
    @Autowired
    private RespostaService respostaService;

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private RespostaRepository respostaRepository;

    public Optional<UpdateRespostaAndParticipanteDTO> updateRespostaAndParticipante(UpdateRespostaAndParticipanteDTO req) {
        UpdateRespostaAndParticipanteDTO response = new UpdateRespostaAndParticipanteDTO();
        response.setRespostas(List.of());

        try {
            ParticipanteModel p = new ParticipanteModel();
            p.setId(req.getParticipanteId());
            p.setNome(req.getNome());
            p.setIdade(req.getIdade());
            p.setGenero(req.getGenero());

            var isValid = this.participanteService.participanteIsValid(p, true);
            var exists = this.participanteService.findById(p.getId()).isPresent();
            if (isValid && exists) {
                this.participanteService.update(p);
                response.setNome(p.getNome());
                response.setIdade(p.getIdade());
                response.setGenero(p.getGenero());
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            return Optional.empty();
        }

        List<RespostaMinDetailsDTO> participanteRespostas = new ArrayList<>();
        try {
            for (RespostaMinDetailsDTO resposta : req.respostas) {
                UpdateRespostaDTO updateRespostaDTO = toUpdateRespostaDTO(req, resposta);

                var perguntaFounded = this.respostaService.findById(updateRespostaDTO.getRespostaId());
                if (perguntaFounded.isEmpty()) {
                    break;
                }

                boolean isPerguntaOwner = Objects.equals(updateRespostaDTO.participanteId, req.getParticipanteId());
                if (isPerguntaOwner) {
                    this.respostaService.update(updateRespostaDTO);
                    participanteRespostas.add(resposta);
                }

            }
        } catch (Exception e) {
            return Optional.empty();
        }

        response.setRespostas(participanteRespostas);
        return Optional.of(response);
    }

    private static UpdateRespostaDTO toUpdateRespostaDTO(UpdateRespostaAndParticipanteDTO req, RespostaMinDetailsDTO resposta) {
        UpdateRespostaDTO updateRespostaDTO = new UpdateRespostaDTO();
        updateRespostaDTO.setRespostaId(resposta.getRespostaId());
        updateRespostaDTO.setParticipanteId(req.getParticipanteId());
        updateRespostaDTO.setDataResposta(resposta.getDataResposta());
        updateRespostaDTO.setPontuacaoTotalDepressao(resposta.getPontuacaoTotalDepressao());
        updateRespostaDTO.setPontuacaoTotalAnsiedade(resposta.getPontuacaoTotalAnsiedade());
        updateRespostaDTO.setPontuacaoTotalEstresse(resposta.getPontuacaoTotalEstresse());
        return updateRespostaDTO;
    }
}
