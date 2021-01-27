package com.figure.store.api.exceptionHandler;

import com.figure.store.domain.exception.ConstraintViolationException;
import com.figure.store.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.figure.store.domain.exception.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();
        OffsetDateTime datetimeNow = OffsetDateTime.now();

        Issue issue = createNoFieldsIssue(status.value(), message, datetimeNow);

        return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = ex.getMessage();
        OffsetDateTime datetimeNow = OffsetDateTime.now();

        Issue issue = createNoFieldsIssue(status.value(), message, datetimeNow);

        return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        HttpStatus status=HttpStatus.METHOD_NOT_ALLOWED;
        Issue issue = createNoFieldsIssue(status.value(), ex.getMessage(), OffsetDateTime.now());

        return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Field> issueFields = new ArrayList<Field>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        errors.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, Locale.ENGLISH);

            issueFields.add(new Field(fieldName, message));
        });

        Integer issueStatusCode = status.value();
        String issueTitle = "Um ou mais campos estão inválidos. "
                            + "Preencha os campos corretamente e tente novamente.";
        OffsetDateTime issueDatetimeNow = OffsetDateTime.now();

        Issue issue = new Issue(issueStatusCode, issueDatetimeNow, issueTitle, issueFields);

        return super.handleExceptionInternal(ex, issue, headers, status, request);
    }

    private Issue createNoFieldsIssue(Integer status, String message, OffsetDateTime datetimeNow) {
        return new Issue(status, message, datetimeNow);
    }
}
