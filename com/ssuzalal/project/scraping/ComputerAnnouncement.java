package com.ssuzalal.project.scraping;

import com.ssuzalal.project.constants.Constants;
import com.ssuzalal.project.mapper.AnnouncementMapper;
import com.ssuzalal.project.vo.AnnouncementVo;
import org.apache.tomcat.util.bcel.Const;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComputerAnnouncement implements AnnouncementScrap {
    @Autowired
    private AnnouncementMapper mapper;

    @Override
    public void scrapAnnouncements() throws Exception {

    }

    @Override
    public List<AnnouncementVo> scrap() throws Exception {
        System.setProperty(Constants.EDGE_DRIVER, Constants.EDGE_DRIVER_PATH);
        WebDriver webDriver = new EdgeDriver();

        String url = Constants.CSE_SSU_URL;
        var waiter = new WebDriverWait(webDriver, 30);
        List<AnnouncementVo> scraps = new ArrayList<>();

        webDriver.get(url);

        var listElements = webDriver.findElements(By.cssSelector("table[summary='글목록'] > tbody > tr"));
        for(var element : listElements) {
            String title, href, date;

            title = element.findElement(By.cssSelector("td:nth-child(2) a")).getText();
            href = element.findElement(By.cssSelector("td:nth-child(2) a")).getAttribute("href");
            date = element.findElement(By.cssSelector("td:nth-child(4)")).getText();

            AnnouncementVo announcement = AnnouncementVo.builder().announcedDate(date).announcedType("컴퓨터학부").title(title).link(href).isCompleted(false).build();
            scraps.add(announcement);

            mapper.insertAnnouncement(announcement);
        }

        webDriver.quit();
        return scraps;
    }
}
