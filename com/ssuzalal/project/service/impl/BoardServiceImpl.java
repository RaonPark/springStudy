package com.ssuzalal.project.service.impl;

import com.ssuzalal.project.mapper.BoardMapper;
import com.ssuzalal.project.service.BoardService;
import com.ssuzalal.project.vo.BoardVo;
import com.ssuzalal.project.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper mapper;

    @Resource(name = "article.path")
    private String articlePath;

    @Override
    public void postArticle(MemberVo member, BoardVo article) throws Exception {
        mapper.addArticle(article);

        JSONObject articleInfo = new JSONObject();
        articleInfo.put("articleInfo", article);

        Path path = Paths.get(articlePath);

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);

            Charset charset = StandardCharsets.UTF_8;
            buffer.put(charset.encode(articleInfo.toString()));

            channel.write(buffer);
        } catch(Exception e) {
            log.info(e.getMessage());
        }
    }
}
