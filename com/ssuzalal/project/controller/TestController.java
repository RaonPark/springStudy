package com.ssuzalal.project.controller;

import com.ssuzalal.project.vo.MemberVo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class TestController {
    @RequestMapping(value = "/ip")
    public ResponseEntity<String> getIp(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(request.getRemoteAddr());
    }
    @RequestMapping(value = "/beforeLoad", method = RequestMethod.POST)
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        MemberVo sessionItem = (MemberVo) session.getAttribute("member");
        if(sessionItem == null) {
            return "/ssuzalal/login";
        }

        session.setAttribute("member", sessionItem);

        return "/ssuzalal/home";
    }
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.POST)
    public MemberVo getLoginInfo(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        return (MemberVo) session.getAttribute("member");
    }
}
