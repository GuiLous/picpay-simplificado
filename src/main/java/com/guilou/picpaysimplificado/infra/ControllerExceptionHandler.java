package com.guilou.picpaysimplificado.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.guilou.picpaysimplificado.dtos.ExceptionDTO;
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicatedEntry(DataIntegrityViolationException ex) {
        ExceptionDTO exception = new ExceptionDTO("Duplicated entry", "400");
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralExceptions(Exception ex) {
        ExceptionDTO exception = new ExceptionDTO(ex.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exception);
    }
}
