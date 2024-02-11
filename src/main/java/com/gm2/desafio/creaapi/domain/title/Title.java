package com.gm2.desafio.creaapi.domain.title;

import com.gm2.desafio.creaapi.domain.professional.Professional;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "title")
@Table(name = "title")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "professional_title",
            joinColumns = @JoinColumn(name = "title_id"), inverseJoinColumns = @JoinColumn(name = "professional_id")
    )
    public List<Professional> professionals;
}