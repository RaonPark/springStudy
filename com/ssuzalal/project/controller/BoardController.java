package com.ssuzalal.project.controller;

import com.ssuzalal.project.service.BoardService;
import com.ssuzalal.project.vo.BoardVo;
import com.ssuzalal.project.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/ssuzalal")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/postArticle.do")
    public boolean postArticle(@RequestBody BoardVo boardVo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        MemberVo postMemberInfo = (MemberVo) session.getAttribute("member");

        if(postMemberInfo == null) return false;

        boardService.postArticle(postMemberInfo, boardVo);

        return true;
    }
}
