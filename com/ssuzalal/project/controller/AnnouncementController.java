package com.ssuzalal.project.controller;

import com.ssuzalal.project.constants.Constants;
import com.ssuzalal.project.mapper.AnnouncementMapper;
import com.ssuzalal.project.scraping.SaintAnnouncement;
import com.ssuzalal.project.vo.AnnouncementVo;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/ssuzalal")
public class AnnouncementController {
    @Autowired
    private SaintAnnouncement saintAnnouncement;

    @Autowired
    private AnnouncementMapper mapper;

    @RequestMapping(value = "/bachelor.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> bachelor() throws Exception {
        return mapper.selectAnnouncements(Constants.BACHELOR);
    }

    @RequestMapping(value = "/scholarship.do", method = RequestMethod.POST)
    public String scholarship() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.SCHOLARSHIP);
    }

    @RequestMapping(value = "/exchange.do", method = RequestMethod.POST)
    public String exchange() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.SCHOLARSHIP);
    }

    @RequestMapping(value = "/foreigner.do", method = RequestMethod.POST)
    public String foreigner() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.FOREIGNER);
    }

    @RequestMapping(value = "/recruit.do", method = RequestMethod.POST)
    public String recruit() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.RECRUIT);
    }

    @RequestMapping(value = "/events.do", method = RequestMethod.POST)
    public String events() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.EVENTS);
    }

    @RequestMapping(value = "/teaching.do", method = RequestMethod.POST)
    public String teaching() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.TEACHING_PROFESSION);
    }

    @RequestMapping(value = "/teacher.do", method = RequestMethod.POST)
    public String teacher() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.TEACHER_RECRUIT);
    }

    @RequestMapping(value = "/volunteer.do", method = RequestMethod.POST)
    public String volunteer() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.VOLUNTEER);
    }

    @RequestMapping(value = "/extras.do", method = RequestMethod.POST)
    public String extras() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.ETC);
    }

    @RequestMapping(value = "/covid19.do", method = RequestMethod.POST)
    public String covid19() throws Exception {
        return saintAnnouncement.announcementJsonString(Constants.COVID19);
    }
}
