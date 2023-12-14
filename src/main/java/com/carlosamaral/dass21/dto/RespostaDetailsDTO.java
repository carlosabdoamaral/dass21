package com.carlosamaral.dass21.dto;

import java.time.Instant;

public class RespostaDetailsDTO {
    public Long respostaId;
    public Long participanteId;
    public Integer idade;
    public String genero;
    public Instant dataResposta;
    public Integer pontuacaoTotalAnsiedade;

    public String escalaAnsiedade;

    public Integer pontuacaoTotalDepressao;

    public String escalaDepressao;

    public Integer pontuacaoTotalEstresse;

    public String escalaEstresse;
}
