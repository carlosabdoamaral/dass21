package com.carlosamaral.dass21.repository;

import com.carlosamaral.dass21.model.RespostaModel;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<RespostaModel, Long> {

    @Query(value = "INSERT INTO Respostas (participante_id, data_resposta, pontuacao_total_depressao, pontuacao_total_ansiedade, pontuacao_total_estresse) VALUES (:participante_id, :data_resposta, :pontuacao_total_depressao, :pontuacao_total_ansiedade, :pontuacao_total_estresse) RETURNING *", nativeQuery = true)
    RespostaModel save(
            @Param("participante_id") Long participanteId,
            @Param("data_resposta") Instant dataResposta,
            @Param("pontuacao_total_depressao") Integer pontuacaoTotalDepressao,
            @Param("pontuacao_total_ansiedade") Integer pontuacaoTotalAnsiedade,
            @Param("pontuacao_total_estresse") Integer pontuacaoTotalEstresse
            );

    @Query(value = "UPDATE respostas SET data_resposta = :data_resposta, pontuacao_total_ansiedade = :pontuacao_total_ansiedade, pontuacao_total_estresse = :pontuacao_total_estresse, pontuacao_total_depressao = :pontuacao_total_depressao, participante_id = :participante_id WHERE id = :resposta_id RETURNING *", nativeQuery = true)
    RespostaModel update(
            @Param("resposta_id") Long respostaId,
            @Param("participante_id") Long participanteId,
            @Param("data_resposta") Instant dataResposta,
            @Param("pontuacao_total_depressao") Integer pontuacaoTotalDepressao,
            @Param("pontuacao_total_ansiedade") Integer pontuacaoTotalAnsiedade,
            @Param("pontuacao_total_estresse") Integer pontuacaoTotalEstresse
    );


    @Query(value = "DELETE FROM respostas WHERE participante_id=:id RETURNING 1", nativeQuery = true)
    Integer deleteByParticipanteId(@Param("id") Long id);

    @Query(value = "SELECT * FROM respostas WHERE participante_id=:id", nativeQuery = true)
    List<RespostaModel> findRespostaByParticipanteId(@Param("id") Long id);

    @Query(value = "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM respostas r WHERE id=:resposta_id AND participante_id=:participante_id", nativeQuery = true)
    Boolean participanteIsRespostaOwner(@Param("resposta_id") Long respostaId, @Param("participante_id") Long participanteId);
}
