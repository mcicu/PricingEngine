package ro.master.pricingengine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.master.pricingengine.beans.SimpleRestResponse;

public abstract class AbstractRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) //Exception superclass
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleRestResponse handleException(Exception e) {
        logger.error("Internal exception: ", e);
        return new SimpleRestResponse(e.getMessage());
    }
}
