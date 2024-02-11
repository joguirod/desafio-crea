package com.gm2.desafio.creaapi.services;

import com.gm2.desafio.creaapi.domain.professional.RegistrationStatus;
import com.gm2.desafio.creaapi.domain.title.Title;
import com.gm2.desafio.creaapi.exceptions.*;
import com.gm2.desafio.creaapi.domain.professional.Professional;
import com.gm2.desafio.creaapi.domain.professional.ProfessionalDTO;
import com.gm2.desafio.creaapi.repositories.ProfessionalRepository;
import com.gm2.desafio.creaapi.repositories.TitleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public Professional addTitleToProfessional(Long professionalId, Long titleId) throws NotFoundException, AlreadyHaveException, InvalidOperationException {
        Optional<Professional> professionalOptional = professionalRepository.findById(professionalId);
        Optional<Title> titleOptional = titleRepository.findById(titleId);
        if(professionalOptional.isEmpty()) throw new NotFoundException("Profissional informado não encontrado");
        if(titleOptional.isEmpty()) throw new NotFoundException("Título informado não encontrado");

        Professional professional = professionalOptional.get();
        Title title = titleOptional.get();

        if(verifyProfessionalIsCancelled(professional)){
            throw new InvalidOperationException("Não é permitido adicionar título a um profissional com registro cancelado");
        } else{
            if(professional.getRegistrationStatus() == null){
                activeProfessional(professional);
                professional.setUniqueCode();
                professional.addTitle(title);
            } else{
                if(verifyProfessionalHasTitle(professional, title)){
                    throw new AlreadyHaveException("O profissional já possui o título informado");
                } else professional.addTitle(title);
            }
        }

        saveProfessional(professional);
        return professional;
    }


    private void activeProfessional(Professional professional) {
        professional.setRegistrationStatus(RegistrationStatus.ACTIVE);
    }

    private boolean verifyProfessionalIsActive(Professional professional){
        return professional.getRegistrationStatus() == RegistrationStatus.ACTIVE;
    }

    private boolean verifyProfessionalIsInactive(Professional professional){
        return professional.getRegistrationStatus() == RegistrationStatus.INACTIVE;
    }

    private boolean verifyProfessionalIsCancelled(Professional professional){
        return professional.getRegistrationStatus() == RegistrationStatus.CANCELLED;
    }

    private boolean verifyProfessionalHasTitle(Professional professional, Title titleToVerify){
        for(Title title : professional.getTitles()){
            if(title.equals(titleToVerify)) return true;
        }

        return false;
    }
}
