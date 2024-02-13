package com.gm2.desafio.creaapi.controller;

import com.gm2.desafio.creaapi.domain.title.Title;
import com.gm2.desafio.creaapi.dtos.TitleDTO;
import com.gm2.desafio.creaapi.exceptions.TitleNotFoundException;
import com.gm2.desafio.creaapi.services.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class TitleController {
    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @PostMapping
    public ResponseEntity<Title> create(@RequestBody TitleDTO titleDTO){
        return new ResponseEntity<>(titleService.createTitle(titleDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Title>> getAll(){
        return new ResponseEntity<>(titleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Title> getById(@PathVariable Long id) throws TitleNotFoundException {
        return new ResponseEntity<>(titleService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Title> update(@RequestBody Title title){
        return new ResponseEntity<>(titleService.update(title), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Title> delete(@PathVariable Long id){
        titleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
