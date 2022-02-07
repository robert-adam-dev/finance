package br.com.adamcast.finance.config.validation;

import br.com.adamcast.finance.exception.DespesaDuplicadaException;
import br.com.adamcast.finance.exception.DespesaNaoEncontradaException;
import br.com.adamcast.finance.exception.ReceitaDuplicadaException;
import br.com.adamcast.finance.exception.ReceitaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDetails> handle(MethodArgumentNotValidException exception) {
        List<ErrorDetails> errorDetailsList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorDetails error = new ErrorDetails(e.getField(), message);
            errorDetailsList.add(error);
        });
        return errorDetailsList;
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handle(DateTimeParseException exception) {
        return new ErrorDetails("data", "Data informada incorreta. O campo data deve ser informado no formato AAAA-MM-DD");
    }

    @ExceptionHandler(ReceitaDuplicadaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDetails handle(ReceitaDuplicadaException exception) {
        return new ErrorDetails("descrição", exception.getMessage());
    }

    @ExceptionHandler(DespesaDuplicadaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDetails handle(DespesaDuplicadaException exception) {
        return new ErrorDetails("descrição", exception.getMessage());
    }

    @ExceptionHandler(ReceitaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handle(ReceitaNaoEncontradaException exception) {
        return new ErrorDetails("id", exception.getMessage());
    }

    @ExceptionHandler(DespesaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handle(DespesaNaoEncontradaException exception) {
        return new ErrorDetails("id", exception.getMessage());
    }
}
