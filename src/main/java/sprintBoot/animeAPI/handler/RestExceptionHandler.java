package sprintBoot.animeAPI.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import sprintBoot.animeAPI.exceptions.BadRequestException;
import sprintBoot.animeAPI.exceptions.BadResquestExceptionDetails;
import sprintBoot.animeAPI.exceptions.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadResquestExceptionDetails> handlerBadRequestException(BadRequestException bre){
       
        return new ResponseEntity<>(BadResquestExceptionDetails
            .builder()
            .msg(bre.getLocalizedMessage())
            .numberStatus(HttpStatus.BAD_REQUEST.value())
            .status("Bad Request")
            .userMsg("Não é possível encontrar o anime com o parametro passado")
            .timeStanp(LocalDateTime.now())
            .build()
            , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
        MethodArgumentNotValidException manve){
                    

        List<FieldError> fieldsErros = manve.getBindingResult().getFieldErrors();

        String fields = fieldsErros.stream().map(FieldError::getField).collect(Collectors.joining(", "));    
        String fieldsMessage = fieldsErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(ValidationExceptionDetails.builder().msg(manve.getLocalizedMessage())
            .status("Validation exception")
            .numberStatus(HttpStatus.BAD_REQUEST.value())
            .userMsg("Field name cannot be empty or null")
            .timeStanp(LocalDateTime.now())
            .fields(fields)
            .fieldsMessage(fieldsMessage)
            .build(), HttpStatus.BAD_REQUEST);
        }
    
}
