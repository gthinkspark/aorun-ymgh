package com.aorun.ymgh.controller;

import com.aorun.ymgh.util.jsonp.Jsonp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //https://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    @ExceptionHandler(MultipartException.class)
    public Object handleError1(MultipartException e) {
        return Jsonp.error(e.getCause().getMessage());
    }
}
