package api.settings;

import api.dtos.responses.ResponseDTO;
import api.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().
                getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return new ResponseEntity<>(
                new ResponseDTO<>(
                        null,
                        null,
                        errors
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseDTO<Object>> handleApiException(ApiException ex) {
        List<String> errors = List.of(ex.getMessage());

        return ResponseEntity
                .status(ex.getStatus())
                .body(new ResponseDTO<>(
                        null,
                        null,
                        errors
                ));
    }
}
