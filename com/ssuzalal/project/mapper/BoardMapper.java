package com.ssuzalal.project.mapper;

import com.ssuzalal.project.vo.BoardVo;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMapper {
    void deleteArticle(BoardVo boardVo) throws Exception;
    void addArticle(BoardVo boardVo) throws Exception;
}
