package com.cg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class HandleErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        int httpErrorCode = getErrorCode(request);

        switch (httpErrorCode) {
            case 401: {
                return new ResponseEntity<>(httpErrorCode, HttpStatus.UNAUTHORIZED);
            }

            case 403: {
                return new ResponseEntity<>(httpErrorCode, HttpStatus.FORBIDDEN);
            }

            default: {
                return new ResponseEntity<>(httpErrorCode, HttpStatus.BAD_REQUEST);
            }
        }
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    @RequestMapping("/403")
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView("error/403");
        String userName = user.getName();
        userName = userName.substring(0, userName.indexOf("@"));
        model.addObject("username", userName);
        return model;
    }

}