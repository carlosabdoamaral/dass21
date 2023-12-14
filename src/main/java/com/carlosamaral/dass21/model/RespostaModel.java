package com.carlosamaral.dass21.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "respostas")
public class RespostaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participante_id")
    private ParticipanteModel participanteModel;

    @NotNull
    @Column(name = "data_resposta", nullable = false)
    private Instant dataResposta;

    @NotNull
    @Column(name = "pontuacao_total_depressao", nullable = false)
    private Integer pontuacaoTotalDepressao;

    @NotNull
    @Column(name = "pontuacao_total_ansiedade", nullable = false)
    private Integer pontuacaoTotalAnsiedade;

    @NotNull
    @Column(name = "pontuacao_total_estresse", nullable = false)
    private Integer pontuacaoTotalEstresse;

    public Long getId() {
        return id;
    }

    public ParticipanteModel getParticipante() {
        return participanteModel;
    }

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

}