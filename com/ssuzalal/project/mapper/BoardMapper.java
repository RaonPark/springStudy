package com.ssuzalal.project.mapper;

import com.ssuzalal.project.vo.BoardVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    void deleteArticle(BoardVo boardVo) throws Exception;
    void insertArticle(BoardVo boardVo) throws Exception;
    int selectArticle(BoardVo boardVo) throws Exception;
    List<BoardVo> selectAllArticles() throws Exception;
}
