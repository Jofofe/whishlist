package com.jofofe.whishlist.exception.handler;

import com.jofofe.whishlist.exception.ClientNotFoundException;
import com.jofofe.whishlist.exception.MaxProductsInWishlistException;
import com.jofofe.whishlist.exception.ProductNotFoundException;
import com.jofofe.whishlist.exception.WishlistNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ClientNotFoundException.class})
    public ResponseEntity clientNotFound(ClientNotFoundException ex, WebRequest request) {
        log.debug("manipulation of ClientNotFoundException...");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MaxProductsInWishlistException.class})
    public ResponseEntity maxProductsInWishlist(MaxProductsInWishlistException ex, WebRequest request) {
        log.debug("manipulation of MaxProductsInWishlistException...");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity productNotFound(ProductNotFoundException ex, WebRequest request) {
        log.debug("manipulation of ProductNotFoundException...");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WishlistNotFoundException.class})
    public ResponseEntity wishlistNotFound(WishlistNotFoundException ex, WebRequest request) {
        log.debug("manipulation of WishlistNotFoundException...");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug("manipulação de MethodArgumentNotValidException...");
        List<String> errors = getErrors(ex);
        return new ResponseEntity<>(errors, status);
    }

    private List<String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
