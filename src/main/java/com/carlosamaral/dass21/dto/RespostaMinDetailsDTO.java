package com.carlosamaral.dass21.dto;

import java.time.Instant;

public class RespostaMinDetailsDTO {
    private Long respostaId;

    public Instant dataResposta;
    private Integer pontuacaoTotalAnsiedade;
    private Integer pontuacaoTotalDepressao;
    private Integer pontuacaoTotalEstresse;

    public Long getRespostaId() {
        return respostaId;
    }

    public void setRespostaId(Long respostaId) {
        this.respostaId = respostaId;
    }

    public Integer getPontuacaoTotalAnsiedade() {
        return pontuacaoTotalAnsiedade;
    }

    public void setPontuacaoTotalAnsiedade(Integer pontuacaoTotalAnsiedade) {
        this.pontuacaoTotalAnsiedade = pontuacaoTotalAnsiedade;
    }

    public Integer getPontuacaoTotalDepressao() {
        return pontuacaoTotalDepressao;
    }

    public void setPontuacaoTotalDepressao(Integer pontuacaoTotalDepressao) {
        this.pontuacaoTotalDepressao = pontuacaoTotalDepressao;
    }

    public Integer getPontuacaoTotalEstresse() {
        return pontuacaoTotalEstresse;
    }

    public void setPontuacaoTotalEstresse(Integer pontuacaoTotalEstresse) {
        this.pontuacaoTotalEstresse = pontuacaoTotalEstresse;
    }

    public Instant getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Instant dataResposta) {
        this.dataResposta = dataResposta;
    }
}
