package com.expickpay.pickpay.excecoes;

import com.expickpay.pickpay.dtos.ExceptionDTO;
import jakarta.persistence.EntityExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("teste", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Essa entidade já está cadastrada", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
