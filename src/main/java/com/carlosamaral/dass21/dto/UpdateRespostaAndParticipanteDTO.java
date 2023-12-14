package com.carlosamaral.dass21.dto;

import java.util.List;

public class UpdateRespostaAndParticipanteDTO {
    public Long participanteId;
    public String nome;
    public Integer idade;
    public String genero;
    public List<RespostaMinDetailsDTO> respostas;

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<RespostaMinDetailsDTO> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaMinDetailsDTO> respostas) {
        this.respostas = respostas;
    }
}
