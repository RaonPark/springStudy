package com.ssuzalal.project.mapper;

import com.ssuzalal.project.vo.AnnouncementVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("announcementMapper")
public interface AnnouncementMapper {
    void insertAnnouncement(AnnouncementVo announcement) throws Exception;
    List<AnnouncementVo> selectAnnouncements(@Param("type") String type) throws Exception;
}
