package GaonNuri.Project.ShoppingMall.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class HttpErrorAdvice {

    /**
     * Common Error
     */
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<ErrorResponse> errorHandler(BindException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(400, e.getAllErrors().get(0).getDefaultMessage())
        );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> errorHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(400, e.getMessage())
        );
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ErrorResponse> errorHandler(NullPointerException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(400, e.getMessage())
        );
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorResponse> errorHandler(RuntimeException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(400, e.getMessage())
        );
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> errorHandler(NoSuchElementException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(400, e.getMessage())
        );
    }

    /**
     * Http Error
     * */
    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<ErrorResponse> errorHandler(HttpClientErrorException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(404, e.getMessage())
        );
    }
    @ExceptionHandler(value = {HttpServerErrorException .class})
    public ResponseEntity<ErrorResponse> errorHandler(HttpServerErrorException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(500, e.getMessage())
        );
    }
    @ExceptionHandler(value = {UnknownHttpStatusCodeException .class})
    public ResponseEntity<ErrorResponse> errorHandler(UnknownHttpStatusCodeException e){
        return ResponseEntity.badRequest().body(
                new ErrorResponse(400, e.getMessage())
        );
    }

}
