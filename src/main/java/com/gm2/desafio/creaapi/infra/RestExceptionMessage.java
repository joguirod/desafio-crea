package com.gm2.desafio.creaapi.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class RestExceptionMessage {
    private HttpStatus status;
    private String message;
}
