package com.ssuzalal.project.scraping;

import com.ssuzalal.project.vo.AnnouncementVo;

import java.util.List;

public interface AnnouncementScrap {
    void scrapAnnouncements() throws Exception;
    List<AnnouncementVo> scrap() throws Exception;
}
