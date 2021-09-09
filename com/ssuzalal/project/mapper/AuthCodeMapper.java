package com.ssuzalal.project.mapper;

import com.ssuzalal.project.vo.AuthCodeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeMapper {
    void insertAuthCode(@Param("email") String email, @Param("code") String code) throws Exception;
    String selectAuthCode(@Param("email") String email) throws Exception;
    void updateAuthCode(AuthCodeVo memberCode) throws Exception;
    void deleteAuthCode(AuthCodeVo memberCode) throws Exception;
}
