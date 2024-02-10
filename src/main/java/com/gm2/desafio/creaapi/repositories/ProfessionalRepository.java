package com.gm2.desafio.creaapi.repositories;

import com.gm2.desafio.creaapi.domain.professional.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}
