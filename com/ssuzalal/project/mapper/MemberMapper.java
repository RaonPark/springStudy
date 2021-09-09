package com.ssuzalal.project.mapper;

import com.ssuzalal.project.vo.MemberVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("memberMapper")
public interface MemberMapper {
    void insertMember(MemberVo member) throws Exception;
    void authMember(MemberVo member) throws Exception;
    MemberVo selectMember(@Param("id") String id) throws Exception;
    StringBuilder selectMemberByEmail(@Param("email") String email) throws Exception;
    String selectPasswordById(@Param("id") String id) throws Exception;
    boolean selectIsAuthenticated(@Param("id") String id) throws Exception;
}
