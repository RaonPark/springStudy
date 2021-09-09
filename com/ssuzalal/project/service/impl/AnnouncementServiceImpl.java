package com.ssuzalal.project.service.impl;

import com.ssuzalal.project.mapper.AnnouncementMapper;
import com.ssuzalal.project.service.AnnouncementService;
import com.ssuzalal.project.vo.AnnouncementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public List<AnnouncementVo> getAllAnnouncements(String type) throws Exception {
        return announcementMapper.selectAnnouncements(type);
    }
}
