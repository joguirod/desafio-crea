package com.gm2.desafio.creaapi.services;

import com.gm2.desafio.creaapi.domain.title.Title;
import com.gm2.desafio.creaapi.dtos.TitleDTO;
import com.gm2.desafio.creaapi.exceptions.TitleNotFoundException;
import com.gm2.desafio.creaapi.repositories.TitleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public void saveTitle(Title title){
        this.titleRepository.save(title);
    }

    public Title createTitle(TitleDTO titleDTO){
        Title title = new Title();
        title.setDescription(titleDTO.description());

        this.saveTitle(title);
        return title;
    }

    public List<Title> getAll(){
        return titleRepository.findAll();
    }

    public Title getById(Long id) throws TitleNotFoundException {
        Optional<Title> title = titleRepository.findById(id);
        if(title.isEmpty()) throw new TitleNotFoundException("O título com id informado não foi encontrado");

        return title.get();
    }

    public Title update(Title title){
        titleRepository.save(title);
        return title;
    }

    public void delete(Long id){
        titleRepository.deleteById(id);
    }
}
