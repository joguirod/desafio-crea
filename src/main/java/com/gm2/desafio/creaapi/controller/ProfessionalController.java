package com.gm2.desafio.creaapi.controller;

import com.gm2.desafio.creaapi.domain.professional.Professional;
import com.gm2.desafio.creaapi.domain.professional.ProfessionalDTO;
import com.gm2.desafio.creaapi.exceptions.AlreadyExistException;
import com.gm2.desafio.creaapi.services.ProfessionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    public final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @PostMapping
    public ResponseEntity<Professional> post(@RequestBody ProfessionalDTO professionalDTO) throws AlreadyExistException {
        return new ResponseEntity<>(professionalService.createProfessional(professionalDTO), HttpStatus.CREATED);
    }
}
