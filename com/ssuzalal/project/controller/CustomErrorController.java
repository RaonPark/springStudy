package com.ssuzalal.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request, Model model) throws Exception {
        var status = request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("status", status);
        model.addAttribute("path", request.getAttribute("javax.servlet.error.request_uri"));
        model.addAttribute("timestamp", new Timestamp(System.currentTimeMillis()));

        var exception = request.getAttribute("javax.servlet.error.exception");
        if(exception != null) {
            var throwable = ((Exception)exception).getCause();
            model.addAttribute("exception", throwable.getClass().getName());
            model.addAttribute("message", throwable.getMessage());
        }

        if(status.equals(HttpStatus.NOT_FOUND.value())) {
            return "/error/404";
        } else {
            return "/error/500";
        }
    }
}
