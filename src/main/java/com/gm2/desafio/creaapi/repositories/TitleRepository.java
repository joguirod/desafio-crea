package com.gm2.desafio.creaapi.repositories;

import com.gm2.desafio.creaapi.domain.title.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
