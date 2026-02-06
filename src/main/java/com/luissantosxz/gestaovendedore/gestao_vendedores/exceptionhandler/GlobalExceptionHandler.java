package com.luissantosxz.gestaovendedore.gestao_vendedores.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFound.class)
    private ResponseEntity<String> naoEncontrado(NotFound execption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(execption.getMessage());
    }

    @ExceptionHandler(BadRequest.class)
    private ResponseEntity<String> requisicaoInvalida(BadRequest exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }



}
