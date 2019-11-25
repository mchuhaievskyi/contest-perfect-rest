package ua.kpi.controller.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.kpi.dto.ApiMessageDto;
import ua.kpi.dto.ValidationErrorDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${message.template.validation-error}")
    private String validationErrorTemplate;

    @Value("${message.entity-not-found}")
    private String entityNotFoundMessage;

    @Value("${message.internal-server-error}")
    private String internalServerErrorMessage;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format(validationErrorTemplate,
                        fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ValidationErrorDto(errors), headers, status);

    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    private ResponseEntity<ApiMessageDto> handleEntityNotFoundException() {
        return new ResponseEntity<>(new ApiMessageDto(entityNotFoundMessage), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiMessageDto> handleException() {
        return new ResponseEntity<>(new ApiMessageDto(internalServerErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
