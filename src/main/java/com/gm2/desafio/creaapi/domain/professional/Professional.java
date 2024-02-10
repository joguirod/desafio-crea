package com.gm2.desafio.creaapi.domain.professional;

import com.gm2.desafio.creaapi.domain.title.Title;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "professional")
@Table(name = "professional")
@Data
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
    private LocalDateTime birthdate;

    @Column(nullable = false)
    private String phoneNumber;

    private ProfessionalType professionalType;
    private RegistrationStatus registrationStatus;
    private LocalDateTime visaDate; // data de visto
    private LocalDateTime registrationDate; // data de registro

    // um profissional pode se relacionar com vários titulos e um titulo pode se relacionar com vários profissionais, relação N pra N
    @ManyToMany(mappedBy = "professionals")
    private List<Title> titles;
}
