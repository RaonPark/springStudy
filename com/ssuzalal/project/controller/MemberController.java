package com.ssuzalal.project.controller;

import com.ssuzalal.project.constants.Constants;
import com.ssuzalal.project.service.MemberService;
import com.ssuzalal.project.service.impl.MailSenderService;
import com.ssuzalal.project.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/ssuzalal")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MailSenderService mailSenderService;

    @RequestMapping(value = "/goToRegister.do", method = RequestMethod.POST)
    public String goToRegister() throws Exception {
        return "/register";
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String register(@RequestBody @Valid MemberVo member) throws Exception {
        StringBuilder code = memberService.addMember(member);
        mailSenderService.sendAuthMail(member.getEmail(), code.toString());

        log.info("ID : " + member.getId() + ", EMAIL : " + member.getEmail() + ", CODE : " + code);

        return "환영합니다.";
    }

    @RequestMapping(value = "/authMember.do", method = RequestMethod.POST)
    public Map<String, Object> authMember(@RequestBody Map<String, String> codeMap, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        HttpSession session = request.getSession();
        MemberVo member = (MemberVo) session.getAttribute("member");

        if(member == null) {
            resultMap.put("result", false);
            resultMap.put("msg", "아이디를 찾을 수 없습니다. 이 오류가 계속되면 관리자에게 연락해주세요.");
            return resultMap;
        }

        String code = codeMap.get("code");

        log.info("member id : " + member.getId());
        log.info("member email : " + member.getEmail());
        log.info("member code : " + code);

        boolean result =  memberService.isAuthCodeSame(member, code);
        resultMap.put("result", result);
        resultMap.put("msg", "code");
        return resultMap;
    }

    @RequestMapping(value = "/requestAuthCode.do", method = RequestMethod.POST)
    public String requestAuthCode(String email, Model model) throws Exception {
        memberService.requestAnotherCode(email);
        model.addAttribute("msg", "인증요청 이메일을 다시 보냈습니다.");
        return "redirect:/auth";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> login(@RequestBody Map<String, Object> memberInfo, HttpServletRequest request) throws Exception {
        String id = (String)memberInfo.get("id");
        String password = (String)memberInfo.get("password");
        Map<String, Object> resultMap = new HashMap<>();

        log.info("id : " + id + " password : " + password);

        boolean isMatched = memberService.login(id, password);
        if(isMatched) {
            boolean isAuthenticated = memberService.isEmailAuthenticated(id);
            log.info("login id : " + id);
            resultMap.put("msg", "로그인 완료.");
            resultMap.put("isAuthenticated", isAuthenticated);

            MemberVo member = memberService.getMemberBasicInfo(id);

            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        } else {
            resultMap.put("msg", "아이디와 비밀번호를 다시 입력해주세요.");
        }
        return resultMap;
    }

    @RequestMapping(value = "/findIdPassword.do", method = RequestMethod.POST)
    public String findIdPassword(@Nullable String id, String email) throws Exception {
        String result = memberService.findIdAndPassword(id, email);

        if(result.compareTo("OK") == 0)
            mailSenderService.sendChangePasswordMail(email);
        else if(result.compareTo("NO") == 0)
            return "redirect:/findIdPassword";
        else if(result.compareTo(Constants.NO_ID) == 0)
            return "redirect:/findIdPassword";

        return "/findIdPassword";
    }


}
