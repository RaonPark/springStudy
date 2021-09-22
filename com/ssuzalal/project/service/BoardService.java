package com.ssuzalal.project.service;

import com.ssuzalal.project.vo.BoardVo;
import com.ssuzalal.project.vo.MemberVo;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {
    void postArticle(MemberVo member, BoardVo article) throws Exception;
}
