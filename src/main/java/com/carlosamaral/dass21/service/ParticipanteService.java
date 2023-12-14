package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.model.ParticipanteModel;
import com.carlosamaral.dass21.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipanteService {
    private final ParticipanteRepository repository;
    private final RespostaService respostaService;
    public ParticipanteService(ParticipanteRepository repository, RespostaService respostaService) {
        this.repository = repository;
        this.respostaService = respostaService;
    }

    public Boolean participanteIsValid(ParticipanteModel participanteModel, Boolean mustCheckId) {
        if (mustCheckId && participanteModel.getId() <= 0) return false;

        var nameIsEmpty = participanteModel.getNome().isEmpty();
        var ageIsLessThanZero = participanteModel.getIdade() <= 0;
        var ageIsGreaterThanMax = participanteModel.getIdade() >= 999;
        var genderIsValid = Objects.equals(participanteModel.getGenero().toUpperCase(), "MASCULINO") || Objects.equals(participanteModel.getGenero().toUpperCase(), "FEMININO");
        return !nameIsEmpty && !ageIsLessThanZero && !ageIsGreaterThanMax && genderIsValid;
    }

    public ParticipanteModel save(ParticipanteModel participanteModel) {
        return this.repository.save(participanteModel);
    }

    public Optional<ParticipanteModel> findById(Long id) {
        return this.repository.findById(id);
    }

    public List<ParticipanteModel> findAll() {
        return this.repository.findAll();
    }

    public ParticipanteModel update(ParticipanteModel body) {
        return this.repository.save(body);
    }

    public void deleteById(Long id) {
        this.respostaService.deleteByParticipanteId(id);
        this.repository.deleteById(id);
    }
}
