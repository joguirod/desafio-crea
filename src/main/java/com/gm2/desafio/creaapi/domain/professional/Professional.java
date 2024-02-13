package com.gm2.desafio.creaapi.domain.professional;

import com.gm2.desafio.creaapi.domain.title.Title;
import com.gm2.desafio.creaapi.dtos.ProfessionalDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "professional")
@Table(name = "professional")
@Data
@NoArgsConstructor
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uniqueCode;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private ProfessionalType professionalType;

    private RegistrationStatus registrationStatus;
    private LocalDate visaDate; // data de visto
    private LocalDate registrationDate; // data de registro

    // um profissional pode se relacionar com vários titulos e um titulo pode se relacionar com vários profissionais, relação N pra N
    @ManyToMany(mappedBy = "professionals")
    private List<Title> titles;

    public Professional(ProfessionalDTO professionalDTO){
        this.name = professionalDTO.name();
        this.email = professionalDTO.email();
        this.password = professionalDTO.password();
        this.birthdate = professionalDTO.birthdate();
        this.phoneNumber = professionalDTO.phoneNumber();
        this.professionalType = professionalDTO.professionalType();
        this.registrationDate = professionalDTO.registrationDate();
        this.visaDate = professionalDTO.visaDate();
    }

    public void addTitle(Title title){
        this.titles.add(title);
    }

    // O código único escolhido foi a concatenação do próprio ID à string "PI"
    public void setUniqueCode(){
        this.uniqueCode = id.toString() + "PI";
    }
}
