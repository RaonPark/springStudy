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
    public @ResponseBody List<AnnouncementVo> scholarship() throws Exception {
        return mapper.selectAnnouncements(Constants.SCHOLARSHIP);
    }

    @RequestMapping(value = "/exchange.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> exchange() throws Exception {
        return mapper.selectAnnouncements(Constants.SCHOLARSHIP);
    }

    @RequestMapping(value = "/foreigner.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> foreigner() throws Exception {
        return mapper.selectAnnouncements(Constants.FOREIGNER);
    }

    @RequestMapping(value = "/recruit.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> recruit() throws Exception {
        return mapper.selectAnnouncements(Constants.RECRUIT);
    }

    @RequestMapping(value = "/events.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> events() throws Exception {
        return mapper.selectAnnouncements(Constants.EVENTS);
    }

    @RequestMapping(value = "/teaching_profession.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> teaching() throws Exception {
        return mapper.selectAnnouncements(Constants.TEACHING_PROFESSION);
    }

    @RequestMapping(value = "/teacher_recruit.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> teacher() throws Exception {
        return mapper.selectAnnouncements(Constants.TEACHER_RECRUIT);
    }

    @RequestMapping(value = "/volunteer.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> volunteer() throws Exception {
        return mapper.selectAnnouncements(Constants.VOLUNTEER);
    }

    @RequestMapping(value = "/etc.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> extras() throws Exception {
        return mapper.selectAnnouncements(Constants.ETC);
    }

    @RequestMapping(value = "/covid19.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> covid19() throws Exception {
        return mapper.selectAnnouncements(Constants.COVID19);
    }

    @RequestMapping(value = "/cse.do", method = RequestMethod.POST)
    public @ResponseBody List<AnnouncementVo> cse() throws Exception {
        return mapper.selectAnnouncements(Constants.CSE);
    }
}
