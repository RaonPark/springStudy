package com.ssuzalal.project.service;

import com.ssuzalal.project.vo.MemberVo;
import org.apache.ibatis.annotations.Param;

public interface MemberService {
    StringBuilder addMember(MemberVo memberVo) throws Exception;
    boolean isAuthCodeSame(MemberVo member, String code) throws Exception;
    void requestAnotherCode(String email) throws Exception;
    boolean login(String id, String password) throws Exception;
    String findIdAndPassword(String id, String email) throws Exception;
    boolean isEmailAuthenticated(String id) throws Exception;
    MemberVo getMemberBasicInfo(String id) throws Exception;
}
