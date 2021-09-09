package com.ssuzalal.project.service.impl;

import com.ssuzalal.project.constants.Constants;
import com.ssuzalal.project.helper.CodeGenerator;
import com.ssuzalal.project.mapper.AuthCodeMapper;
import com.ssuzalal.project.mapper.MemberMapper;
import com.ssuzalal.project.service.MemberService;
import com.ssuzalal.project.vo.AuthCodeVo;
import com.ssuzalal.project.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Slf4j
@Service
public class MemberServiceImpl implements Serializable, MemberService {
    @Serial
    private static final long serialVersionUID = 5185719643372867115L;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AuthCodeMapper authCodeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public StringBuilder addMember(MemberVo member) throws Exception {
        member.setAuthorized(false);
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        log.info(member.getId() + " has registered. password : " + member.getPassword());

        memberMapper.insertMember(member);

        StringBuilder code = CodeGenerator.generate();
        addAuthCode(member.getEmail(), code.toString());

        return code;
    }

    private void addAuthCode(String email, String code) throws Exception {
        authCodeMapper.insertAuthCode(email, code);
    }

    @Override
    public boolean isAuthCodeSame(MemberVo member, String code) throws Exception {
        String authCode = authCodeMapper.selectAuthCode(member.getEmail());
        boolean result = authCode.compareTo(code) == 0;
        if(result) {
            log.info(member.getId() + " has certificated.");
            authMember(member);
            afterAuth(member.getEmail(), code);
        }

        return result;
    }

    private void authMember(MemberVo member) throws Exception {
        memberMapper.authMember(member);
    }

    private void afterAuth(String email, String code) throws Exception {
        authCodeMapper.deleteAuthCode(new AuthCodeVo(email, code));
    }

    @Override
    public void requestAnotherCode(String email) throws Exception {
        StringBuilder code = CodeGenerator.generate();
        authCodeMapper.updateAuthCode(new AuthCodeVo(email, code.toString()));
    }

    @Override
    public boolean login(String id, String password) throws Exception {
        String realPassword = memberMapper.selectPasswordById(id);
        if(realPassword == null) return false;
        return passwordEncoder.matches(password, realPassword);
    }

    @Override
    public String findIdAndPassword(String id, String email) throws Exception {
        if(id == null) return findId(email).toString();

        boolean resultOfFindPassword = findPassword(id, email);
        if(resultOfFindPassword) return "OK";

        return "NO";
    }

    private StringBuilder findId(String email) throws Exception {
        StringBuilder id = memberMapper.selectMemberByEmail(email);
        if(id.isEmpty()) return new StringBuilder(Constants.NO_ID);
        return id;
    }

    private boolean findPassword(String id, String email) throws Exception {
        StringBuilder result = findId(email);

        if(result.compareTo(new StringBuilder(Constants.NO_ID)) == 0)
            return false;

        return result.compareTo(new StringBuilder(id)) == 0;
    }

    @Override
    public boolean isEmailAuthenticated(String id) throws Exception {
        return memberMapper.selectIsAuthenticated(id);
    }

    @Override
    public MemberVo getMemberBasicInfo(String id) throws Exception {
        return memberMapper.selectMember(id);
    }
}
