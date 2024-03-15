package com.bongsamaru.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController implements ErrorController {

    public String getErrorPath() {
        return null;
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            }else if(statusCode == HttpStatus.BAD_REQUEST.value()){
                return "error/400";
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
            	return "error/500";
            }else {
            	return "error/else";            	
            }
        }

        return "error/error";
    }

}