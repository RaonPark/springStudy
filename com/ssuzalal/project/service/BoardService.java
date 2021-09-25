package com.ssuzalal.project.service;

import com.ssuzalal.project.vo.BoardVo;
import com.ssuzalal.project.vo.MemberVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {
    void postArticle(MemberVo member, BoardVo article) throws Exception;
    boolean deleteArticle(MemberVo member, BoardVo article) throws Exception;
    List<BoardVo> getArticles() throws Exception;
}
