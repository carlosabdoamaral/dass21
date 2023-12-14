package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.dto.RespostaDetailsDTO;
import com.carlosamaral.dass21.dto.SaveRespostaDTO;
import com.carlosamaral.dass21.dto.UpdateRespostaDTO;
import com.carlosamaral.dass21.enums.SymptomTypeEnum;
import com.carlosamaral.dass21.model.ParticipanteModel;
import com.carlosamaral.dass21.model.RespostaModel;
import com.carlosamaral.dass21.repository.ParticipanteRepository;
import com.carlosamaral.dass21.repository.RespostaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaService{
    private final RespostaRepository respostaRepository;
    private final SintomaService sintomaService;
    private final ParticipanteRepository participanteRepository;
    public RespostaService(RespostaRepository respostaRepository, SintomaService sintomaService, ParticipanteRepository participanteRepository) {
        this.respostaRepository = respostaRepository;
        this.sintomaService = sintomaService;
        this.participanteRepository = participanteRepository;
    }

    public RespostaDetailsDTO toDetailsDTO(RespostaModel resp) {
        RespostaDetailsDTO dto = new RespostaDetailsDTO();
        dto.respostaId = resp.getId();
        dto.participanteId = resp.getParticipante().getId();
        dto.idade = resp.getParticipante().getIdade();
        dto.genero = resp.getParticipante().getGenero();
        dto.dataResposta = resp.getDataResposta();

        dto.pontuacaoTotalAnsiedade = resp.getPontuacaoTotalAnsiedade();
        dto.escalaAnsiedade = this.sintomaService.getScaleByType(
                SymptomTypeEnum.ANSIEDADE,
                resp.getPontuacaoTotalAnsiedade()
        ).getDescricao();

        dto.pontuacaoTotalDepressao = resp.getPontuacaoTotalDepressao();
        dto.escalaDepressao = this.sintomaService.getScaleByType(
                SymptomTypeEnum.DEPRESSAO,
                resp.getPontuacaoTotalDepressao()
        ).getDescricao();

        dto.pontuacaoTotalEstresse = resp.getPontuacaoTotalEstresse();
        dto.escalaEstresse = this.sintomaService.getScaleByType(
                SymptomTypeEnum.ESTRESSE,
                resp.getPontuacaoTotalEstresse()
        ).getDescricao();

        return dto;
    }

    private Boolean defaultValidationIsOk(Optional<SaveRespostaDTO> saveRespostaDTO, Optional<UpdateRespostaDTO> updateRespostaDTO) {
        boolean dataRespostaIsNotNull = false;
        boolean pontuacaoTotalDepressaoIsValid = false;
        boolean pontuacaoTotalAnsiedadeIsValid = false;
        boolean pontuacaoTotalEstresseIsValid = false;

        if (saveRespostaDTO.isPresent()) {
            dataRespostaIsNotNull = saveRespostaDTO.get().getDataResposta() != null;
            pontuacaoTotalDepressaoIsValid = saveRespostaDTO.get().getPontuacaoTotalDepressao() >= 0;
            pontuacaoTotalAnsiedadeIsValid = saveRespostaDTO.get().getPontuacaoTotalAnsiedade() >= 0;
            pontuacaoTotalEstresseIsValid = saveRespostaDTO.get().getPontuacaoTotalEstresse() >= 0;
        }

        if (updateRespostaDTO.isPresent()) {
            dataRespostaIsNotNull = updateRespostaDTO.get().getDataResposta() != null;
            pontuacaoTotalDepressaoIsValid = updateRespostaDTO.get().getPontuacaoTotalDepressao() >= 0;
            pontuacaoTotalAnsiedadeIsValid = updateRespostaDTO.get().getPontuacaoTotalAnsiedade() >= 0;
            pontuacaoTotalEstresseIsValid = updateRespostaDTO.get().getPontuacaoTotalEstresse() >= 0;
        }

        return dataRespostaIsNotNull && pontuacaoTotalDepressaoIsValid && pontuacaoTotalAnsiedadeIsValid && pontuacaoTotalEstresseIsValid;
    }
    public Boolean respostaIsValid(SaveRespostaDTO resp) {
        var participanteExists = this.participanteRepository.findById(resp.getParticipanteId()).isPresent();
        return participanteExists && defaultValidationIsOk(Optional.of(resp), Optional.empty());
    }

    public RespostaModel save(SaveRespostaDTO body) {
        Optional<ParticipanteModel> p = this.participanteRepository.findById(body.getParticipanteId());
        if (p.isEmpty()) return null;

        return this.respostaRepository.save(
                body.getParticipanteId(),
                body.getDataResposta(),
                body.getPontuacaoTotalDepressao(),
                body.getPontuacaoTotalAnsiedade(),
                body.getPontuacaoTotalEstresse()
        );
    }

    public Optional<RespostaDetailsDTO> findById(Long id) {
        Optional<RespostaModel> p = this.respostaRepository.findById(id);
        return p.map(this::toDetailsDTO);
    }

    public List<RespostaDetailsDTO> findAll() {
        List<RespostaModel> list = this.respostaRepository.findAll();
        return list.stream().map(this::toDetailsDTO).toList();
    }

    public RespostaDetailsDTO update(UpdateRespostaDTO body) {
        this.respostaRepository.update(
            body.getRespostaId(),
            body.getParticipanteId(),
            body.getDataResposta(),
            body.getPontuacaoTotalDepressao(),
            body.getPontuacaoTotalAnsiedade(),
            body.getPontuacaoTotalEstresse()
        );

        Optional<RespostaModel> respostaModel = this.respostaRepository.findById(body.getRespostaId());
        return respostaModel.map(this::toDetailsDTO).orElse(null);
    }

    public void deleteById(Long id) {
        this.respostaRepository.deleteById(id);
    }

    public void deleteByParticipanteId(Long id) {
        this.respostaRepository.deleteByParticipanteId(id);
    }

    public List<RespostaDetailsDTO> findByParticipanteId(Long id) {
        List<RespostaModel> list = this.respostaRepository.findRespostaByParticipanteId(id);
        return list.stream().map(this::toDetailsDTO).toList();
    }
}
