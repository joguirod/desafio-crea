package com.gm2.desafio.creaapi.repositories;

import com.gm2.desafio.creaapi.domain.professional.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    public Optional<Professional> findProfessionalByEmail(String email);
}
