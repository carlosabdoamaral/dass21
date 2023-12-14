package com.carlosamaral.dass21.dto;


import java.time.Instant;

public class UpdateRespostaDTO {
    public Long respostaId;
    public Long participanteId;
    public Instant dataResposta;
    public Integer pontuacaoTotalDepressao;
    public Integer pontuacaoTotalAnsiedade;
    public Integer pontuacaoTotalEstresse;

    public Instant getDataResposta() {
        return dataResposta;
    }

    public Integer getPontuacaoTotalDepressao() {
        return pontuacaoTotalDepressao;
    }

    public Integer getPontuacaoTotalAnsiedade() {
        return pontuacaoTotalAnsiedade;
    }

    public Integer getPontuacaoTotalEstresse() {
        return pontuacaoTotalEstresse;
    }

    public Long getRespostaId() {
        return respostaId;
    }

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setRespostaId(Long respostaId) {
        this.respostaId = respostaId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    public void setDataResposta(Instant dataResposta) {
        this.dataResposta = dataResposta;
    }

    public void setPontuacaoTotalDepressao(Integer pontuacaoTotalDepressao) {
        this.pontuacaoTotalDepressao = pontuacaoTotalDepressao;
    }

    public void setPontuacaoTotalAnsiedade(Integer pontuacaoTotalAnsiedade) {
        this.pontuacaoTotalAnsiedade = pontuacaoTotalAnsiedade;
    }

    public void setPontuacaoTotalEstresse(Integer pontuacaoTotalEstresse) {
        this.pontuacaoTotalEstresse = pontuacaoTotalEstresse;
    }
}
