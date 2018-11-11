package sb.vodafone.test.vodafonetest.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<?> InvalidArgsHandler(MethodArgumentNotValidException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getBindingResult().getFieldError().getDefaultMessage());
  }
  
  @ExceptionHandler(PhoneNumberFormatException.class )
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<?> InvalidFormatHandler(PhoneNumberFormatException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }
  
  @ExceptionHandler(NumberInvalidException.class )
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<?> InconsistentDataHandler(NumberInvalidException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }
  
  @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<?> DuplicateNumberHandler(org.hibernate.exception.ConstraintViolationException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already exists");
  }
  
  @ExceptionHandler(NumberNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<?> DuplicateNumberHandler(NumberNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile number not found");
  }
  
 
}