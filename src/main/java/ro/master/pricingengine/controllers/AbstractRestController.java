package ro.master.pricingengine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.master.pricingengine.beans.SimpleRestResponse;

import java.text.MessageFormat;
import java.util.Optional;

public abstract class AbstractRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleRestResponse handleException(MethodArgumentNotValidException e) {
        Optional<String> fields = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField())
                .reduce((a, b) -> a + ", " + b);
        return new SimpleRestResponse(MessageFormat.format("Following fields are missing or have invalid values: [{0}]", fields.get()));
    }

    @ExceptionHandler(Exception.class) //Exception superclass
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleRestResponse handleException(Exception e) {
        logger.error("Internal exception: ", e);
        return new SimpleRestResponse(e.getMessage());
    }
}
