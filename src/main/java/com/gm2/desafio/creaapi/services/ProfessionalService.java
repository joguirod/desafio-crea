package com.gm2.desafio.creaapi.services;

import com.gm2.desafio.creaapi.domain.professional.ProfessionalType;
import com.gm2.desafio.creaapi.domain.professional.RegistrationStatus;
import com.gm2.desafio.creaapi.domain.title.Title;
import com.gm2.desafio.creaapi.dtos.TitleIdDTO;
import com.gm2.desafio.creaapi.exceptions.*;
import com.gm2.desafio.creaapi.domain.professional.Professional;
import com.gm2.desafio.creaapi.dtos.ProfessionalDTO;
import com.gm2.desafio.creaapi.repositories.ProfessionalRepository;
import com.gm2.desafio.creaapi.repositories.TitleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final TitleRepository titleRepository;

    private final TitleService titleService;

    public ProfessionalService(ProfessionalRepository professionalRepository, TitleRepository titleRepository, TitleService titleService) {
        this.professionalRepository = professionalRepository;
        this.titleRepository = titleRepository;
        this.titleService = titleService;
    }

    public void saveProfessional(Professional professional){
        professionalRepository.save(professional);
    }
    public Professional createProfessional(ProfessionalDTO professionalDTO) throws AlreadyExistException, InvalidOperationException {
        if(professionalRepository.findProfessionalByEmail(professionalDTO.email()).isPresent()){
            throw new AlreadyExistException("Um profissional com o email fornecido já existe");
        }

        Professional professional = new Professional(professionalDTO);

        verifyProfessionalType(professional);

        this.saveProfessional(professional);
        return professional;
    }

    public List<Professional> getAll(){
        return professionalRepository.findAll();
    }

    public Professional getById(Long id) throws ProfessionalNotFoundException {
        Optional<Professional> professional = professionalRepository.findById(id);
        if (professional.isEmpty()) throw new ProfessionalNotFoundException("Nenhum profissional com o id informado foi encontrado");
        return professional.get();
    }

    public Professional getByEmail(String email) throws ProfessionalNotFoundException {
        Optional<Professional> professional = professionalRepository.findProfessionalByEmail(email);
        if (professional.isEmpty()) throw new ProfessionalNotFoundException("Nenhum profissional com o email informado foi encontrado");
        return professional.get();
    }

    public Professional getByUniqueCode(String uniqueCode) throws ProfessionalNotFoundException {
        Optional<Professional> professional = professionalRepository.findProfessionalByUniqueCode(uniqueCode);
        if(professional.isEmpty()) throw new ProfessionalNotFoundException("Nenhum profissional com o código único informado foi encontrado");
        return professional.get();
    }

    public Professional update(Professional professionalToEdit) throws ProfessionalNotFoundException, InvalidOperationException {
        Professional professionalBefore = getById(professionalToEdit.getId());

        verifyProfessionalType(professionalToEdit);

        // verify if the email sent in the request is different of the one that was already in use by the professional
        if(!professionalToEdit.getEmail().equals(professionalBefore.getEmail())){
            if(professionalRepository.findProfessionalByEmail(professionalToEdit.getEmail()).isPresent()){
                throw new InvalidOperationException("O email fornecido já está sendo utilizado");
            }
        }

        // verify if the unique code sent in the request is different of the one that was already in use by the professional
        if(!professionalToEdit.getUniqueCode().equals(professionalBefore.getUniqueCode())){
            if(professionalRepository.findProfessionalByUniqueCode(professionalToEdit.getUniqueCode()).isPresent()){
                throw new InvalidOperationException("O código único fornecido já está sendo utilizado");
            }
        }

        this.saveProfessional(professionalToEdit);
        return professionalToEdit;
    }

    public void deleteProfessional(Long id) throws ProfessionalNotFoundException {
        Professional professional = getById(id);

        professionalRepository.delete(professional);
    }

    @Transactional
    public Professional addTitleToProfessional(Long professionalId, TitleIdDTO dto) throws ProfessionalNotFoundException, AlreadyHaveException, InvalidOperationException, TitleNotFoundException {
        Optional<Professional> professionalOptional = professionalRepository.findById(professionalId);
        Optional<Title> titleOptional = titleRepository.findById(dto.titleId());
        if(professionalOptional.isEmpty()) throw new ProfessionalNotFoundException("Profissional informado não encontrado");
        if(titleOptional.isEmpty()) throw new TitleNotFoundException("Título informado não encontrado");

        Professional professional = professionalOptional.get();
        Title title = titleOptional.get();


        if(verifyProfessionalIsCancelled(professional)){
            throw new InvalidOperationException("Não é permitido adicionar título a um profissional com registro cancelado");
        } else{
            if(professional.getRegistrationStatus() == null){
                setProfessionalStatusToActive(professional);
                professional.setUniqueCode();
            }
            if(verifyProfessionalHasTitle(professional, title)){
                throw new AlreadyHaveException("O profissional já possui o título informado");
            }
            professional.addTitle(title);
        }

<<<<<<< HEAD
        this.saveProfessional(professional);
=======
        professionalRepository.save(professional);
>>>>>>> 84013fcf41bcad65b150bfe1fd121c3bbf430f8d

        return professional;
    }

    public Professional activeProfessional(Long id) throws ProfessionalNotFoundException, AlreadyIsException {
        Professional professional = getById(id);
        // verify if the professional already is an active professional
        if(verifyProfessionalIsActive(professional)) throw new AlreadyIsException("O profissional já é ativo");

        setProfessionalStatusToActive(professional);
        saveProfessional(professional);
        return professional;
    }

    public Professional inactivateProfessional(Long id) throws ProfessionalNotFoundException, AlreadyIsException {
        Professional professional = getById(id);
        // verify if the professional already is an inactive professional
        if(verifyProfessionalIsInactive(professional)) throw new AlreadyIsException("O profissional já é inativo");

        professional.setRegistrationStatus(RegistrationStatus.INACTIVE);
        saveProfessional(professional);
        return professional;
    }

    public Professional cancelProfessional(Long id) throws ProfessionalNotFoundException, AlreadyIsException {
        Professional professional = getById(id);
        // verify if the professional already is a cancelled professional
        if(verifyProfessionalIsCancelled(professional)) throw new AlreadyIsException("O profissional já está cancelado");

        professional.setRegistrationStatus(RegistrationStatus.CANCELLED);
        saveProfessional(professional);
        return professional;
    }

    private void setProfessionalStatusToActive(Professional professional) {
        professional.setRegistrationStatus(RegistrationStatus.ACTIVE);
    }

    // verify if the dates given matches the professional type, the professional cant be registred and have a visa date and vice versa
    private void verifyProfessionalType(Professional professional) throws InvalidOperationException {
        if(professional.getProfessionalType() == ProfessionalType.REGISTERED && professional.getVisaDate() != null){
            throw new InvalidOperationException("Não é permitido adicionar data de visto para um profissional do tipo registrado");
        }
        if(professional.getProfessionalType() == ProfessionalType.ENDORSED && professional.getRegistrationDate() != null){
            throw new InvalidOperationException("Não é permitido adicionar data de registro para um profissional do tipo visado");
        }
    }

    private boolean verifyProfessionalExist(Professional professional){
        return professionalRepository.findById(professional.getId()).isPresent();
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
