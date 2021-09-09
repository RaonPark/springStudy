package com.ssuzalal.project.service;

import com.ssuzalal.project.vo.AnnouncementVo;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementVo> getAllAnnouncements(String type) throws Exception;
}
