package com.damirkan.shareservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ShareNodFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ShareNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String shareNotFoundHandler(ShareNotFoundException ex) {
        return ex.getMessage();
    }
}
