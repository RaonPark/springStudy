package com.ssuzalal.project.scraping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Example1 {
    public static String crawl() {
        System.setProperty("webdriver.edge.driver", "D:\\webcrawler\\stable\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        driver.get(baseUrl);
        actualTitle = driver.getTitle();

        driver.close();

        if(actualTitle.contentEquals(expectedTitle)) {
            return actualTitle;
        } else {
            return expectedTitle;
        }
    }

    public static String ex2() throws Exception {
        System.setProperty("webdriver.edge.driver", "D:\\webcrawler\\stable\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        String baseUrl = "https://www.facebook.com";
        String tagName = "";

        driver.get(baseUrl);
        tagName = driver.findElement(By.id("email")).getTagName();
        driver.close();

        return tagName;
    }

    public static List<WebElement> ex3() throws Exception {
        System.setProperty("webdriver.edge.driver", "D:\\webcrawler\\stable\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        String baseUrl = String.format("https://scatch.ssu.ac.kr/공지사항/?f&category=%s&keyword", "학사");
        new WebDriverWait(driver, 15);

        driver.get(baseUrl);
        var obj = driver.findElements(By.cssSelector("span.d-inline-blcok m-pt-5"));

        for(WebElement w : obj) {
            System.out.println(w.getText());
        }

        driver.quit();

        return obj;
    }

    public static void ex4() {
        System.setProperty("webdriver.edge.driver", "D:\\webcrawler\\stable\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        String alertMessage = "";
        driver.get("http://jsbin.com/usidix/1");
        driver.findElement(By.cssSelector("input[value=\"GO!\"]")).click();
        alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        System.out.println(alertMessage);
        driver.quit();
    }
}
