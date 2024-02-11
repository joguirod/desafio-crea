package com.gm2.desafio.creaapi.services;

import com.gm2.desafio.creaapi.exceptions.*;
import com.gm2.desafio.creaapi.domain.professional.Professional;
import com.gm2.desafio.creaapi.domain.professional.ProfessionalDTO;
import com.gm2.desafio.creaapi.repositories.ProfessionalRepository;
import com.gm2.desafio.creaapi.repositories.TitleRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;
    private final TitleRepository titleRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository, TitleRepository titleRepository) {
        this.professionalRepository = professionalRepository;
        this.titleRepository = titleRepository;
    }

    public void saveProfessional(Professional professional){
        professionalRepository.save(professional);
    }
    public Professional createProfessional(ProfessionalDTO professionalDTO) throws AlreadyExistException {
        if(professionalRepository.findProfessionalByEmail(professionalDTO.email()).isPresent()){
            throw new AlreadyExistException("Um profissional com o email fornecido já existe");
        }

        Professional professional = new Professional(professionalDTO);
        this.saveProfessional(professional);
        return professional;
    }
}
