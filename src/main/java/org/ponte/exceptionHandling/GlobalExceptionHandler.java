package org.ponte.exceptionHandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        validationErrors.forEach(validationError -> {
            log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppUserNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleUserNotFoundException(AppUserNotFoundException exception) {
        ValidationError validationError = new ValidationError("appUserId",
                "appUser not found with id: " + exception.getAppUserId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactLocationNotFoundException.class)
    public ResponseEntity<List<ValidationError>> ContactLocationNotFoundException(ContactLocationNotFoundException exception) {
        ValidationError validationError = new ValidationError("ContactLocationId",
                "ContactLocation not found with id: " + exception.getContactLocationId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<List<ValidationError>> ContactNotFoundException(ContactNotFoundException exception) {
        ValidationError validationError = new ValidationError("ContactId",
                "Contact not found with id: " + exception.getContactId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailOrPhoneNotValidException.class)
    public ResponseEntity<List<ValidationError>> EmailOrPhoneNotValidException() {
        ValidationError validationError = new ValidationError("Invalid contact information",
                "Entering a valid e-mail or phone number is mandatory.");
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleUserEmailNotFoundException(UserEmailNotFoundException exception) {
        ValidationError validationError = new ValidationError("appUserMail",
                "appUserMail not found with mail: " + exception.getEmail());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<List<ValidationError>> handlePasswordNotValidException(PasswordNotValidException exception) {
        ValidationError validationError = new ValidationError("Password",
                "Invalid password. It must be at least 8 characters long, contain at least one of these [A-Z], and at least one of these [!@#$%^&*()-_+=].");
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<List<ValidationError>> handleDuplicateEmailException(DuplicateEmailException exception) {
        ValidationError validationError = new ValidationError("Email",
                "User has already registered with email: " + exception.getEmail());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
