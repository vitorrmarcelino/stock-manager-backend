package com.vitorrmarcelino.stock_manager.infra;

import com.vitorrmarcelino.stock_manager.dto.ErrorMessageResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.CnpjAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.CpfAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.EmailAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.PasswordsDoesntMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> emailAlreadyUsedHandler(EmailAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ErrorMessageResponseDTO(e.getMessage())));
    }

    @ExceptionHandler(PasswordsDoesntMatchException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> passwordsDoesntMatchHandler(PasswordsDoesntMatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ErrorMessageResponseDTO(e.getMessage())));
    }

    @ExceptionHandler(CnpjAlreadyUsedException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> cnpjAlreadyUsedHandler(CnpjAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ErrorMessageResponseDTO(e.getMessage())));
    }

    @ExceptionHandler(CpfAlreadyUsedException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> cnpjAlreadyUsedHandler(CpfAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ErrorMessageResponseDTO(e.getMessage())));
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> badCredencialsHandler(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(List.of(new ErrorMessageResponseDTO("Invalid credentials")));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> userNotFoundInAuthenticationHandler(InternalAuthenticationServiceException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(List.of(new ErrorMessageResponseDTO("User not found")));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> genericExceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ErrorMessageResponseDTO("Internal server error")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<List<ErrorMessageResponseDTO>> validationExceptionsHandler(MethodArgumentNotValidException e) {
        List<ErrorMessageResponseDTO> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorMessageResponseDTO(error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
