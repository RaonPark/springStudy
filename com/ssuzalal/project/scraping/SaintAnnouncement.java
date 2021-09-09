package com.ssuzalal.project.scraping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssuzalal.project.constants.Constants;
import com.ssuzalal.project.mapper.AnnouncementMapper;
import com.ssuzalal.project.vo.AnnouncementVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaintAnnouncement {
    @Autowired
    private AnnouncementMapper announcementMapper;

    private final ObjectMapper objectMapper;

    public String announcementJsonString(String type) throws Exception {
        return convertToString(scraping(type));
    }

    private List<AnnouncementVo> scraping(String type) throws Exception {
        System.setProperty(Constants.EDGE_DRIVER, Constants.EDGE_DRIVER_PATH);
        WebDriver driver = new EdgeDriver();

        String url = String.format(Constants.SAINT_URL, type);
        var waiter = new WebDriverWait(driver, 15);
        List<AnnouncementVo> scraps = new ArrayList<>();

        driver.get(url);

        var listElements = driver.findElements(By.cssSelector("li.start"));
        for(var listElement : listElements) {
            String date = listElement.findElement(By.cssSelector("div.h2.text-info.font-weight-bold")).getText();
            String title = listElement.findElement(By.cssSelector("span.d-inline-blcok.m-pt-5")).getText();
            String link = listElement.findElement(By.cssSelector("a.text-decoration-none.d-block.text-truncate")).getAttribute("href");

            boolean isCompleted = true;
            String completed = "";

            if(listElement.findElements(By.cssSelector("span.tag.ing")).size() != 0) {
                completed = listElement.findElement(By.cssSelector("span.tag.ing")).getText();
            } else if(listElement.findElements(By.cssSelector("span.tag.com")).size() != 0) {
                completed = listElement.findElement(By.cssSelector("span.tag.com")).getText();
            }

            if(!completed.equals(""))
                isCompleted = !completed.equals(Constants.PROGRESS);

            AnnouncementVo announcement = new AnnouncementVo(Constants.BACHELOR, date, title, link, isCompleted);
            scraps.add(announcement);

            announcementMapper.insertAnnouncement(announcement);
        }

        driver.quit();

        return scraps;
    }

    private List<AnnouncementVo> convertToList(List<AnnouncementVo> scraps) {
        return objectMapper.convertValue(scraps, new TypeReference<>() {});
    }

    private String convertToString(List<AnnouncementVo> convertedList) {
        try {
            return objectMapper.writeValueAsString(convertedList);
        } catch(JsonProcessingException e) {
            log.error("error when convert to string");
            log.error(e.getMessage());
            return "";
        }
    }

    // for test
    public void createJsonToParser() throws Exception {
        String jsonString = convertToString(scraping(Constants.SCHOLARSHIP));
        Path file = Paths.get(".", "test.txt");
        FileChannel channel = FileChannel.open(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        ByteBuffer write = ByteBuffer.allocate(1024 * 1024);
        byte[] jsonBytes = jsonString.getBytes(StandardCharsets.UTF_8);

        for(byte b : jsonBytes) {
            write.put(b);

            write.flip();
            channel.write(write);
            write.clear();
        }
    }
}
