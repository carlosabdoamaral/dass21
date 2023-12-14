package com.carlosamaral.dass21.repository;

import com.carlosamaral.dass21.model.ParticipanteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteModel, Long> {
}
