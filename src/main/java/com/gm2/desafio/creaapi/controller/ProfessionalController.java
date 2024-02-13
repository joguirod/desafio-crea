package com.gm2.desafio.creaapi.controller;

import com.gm2.desafio.creaapi.domain.professional.Professional;
import com.gm2.desafio.creaapi.dtos.ProfessionalDTO;
import com.gm2.desafio.creaapi.dtos.TitleIdDTO;
import com.gm2.desafio.creaapi.exceptions.*;
import com.gm2.desafio.creaapi.services.ProfessionalService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @PostMapping
    public ResponseEntity<Professional> post(@RequestBody ProfessionalDTO professionalDTO) throws AlreadyExistException, InvalidOperationException {
        return new ResponseEntity<>(professionalService.createProfessional(professionalDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{professionalId}/add-title")
    public ResponseEntity<Professional> addTitle(@PathVariable(name = "professionalId") Long professionalId, @RequestBody TitleIdDTO titleDTO) throws
            TitleNotFoundException, ProfessionalNotFoundException, InvalidOperationException, AlreadyHaveException {
        return new ResponseEntity<>(professionalService.addTitleToProfessional(professionalId, titleDTO), HttpStatus.OK);
    }

    @PostMapping("/activate/{professionalId}")
    public ResponseEntity<Professional> activateProfessional(@PathVariable Long professionalId) throws AlreadyIsException, ProfessionalNotFoundException {
        return new ResponseEntity<>(professionalService.activeProfessional(professionalId), HttpStatus.OK);
    }

    @PostMapping("/inactivate/{professionalId}")
    public ResponseEntity<Professional> inactivateProfessional(@PathVariable Long professionalId) throws AlreadyIsException, ProfessionalNotFoundException {
        return new ResponseEntity<>(professionalService.inactivateProfessional(professionalId), HttpStatus.OK);
    }

    @PostMapping("/cancel/{professionalId}")
    public ResponseEntity<Professional> cancelProfessional(@PathVariable Long professionalId) throws AlreadyIsException, ProfessionalNotFoundException {
        return new ResponseEntity<>(professionalService.cancelProfessional(professionalId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Professional>> getAll(){
        return new ResponseEntity<>(professionalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Professional> getById(@PathVariable Long id) throws ProfessionalNotFoundException {
        return new ResponseEntity<>(professionalService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Professional> getByEmail(@PathVariable String email) throws ProfessionalNotFoundException {
        return new ResponseEntity<>(professionalService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/{uniqueCode}")
    public ResponseEntity<Professional> getByUniqueCode(@PathVariable String uniqueCode) throws ProfessionalNotFoundException {
        return new ResponseEntity<>(professionalService.getByUniqueCode(uniqueCode), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Professional> update(@RequestBody Professional professional) throws ProfessionalNotFoundException, InvalidOperationException {
        return new ResponseEntity<>(professionalService.update(professional), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professional> delete(@PathVariable Long id) throws ProfessionalNotFoundException {
        professionalService.deleteProfessional(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
