package com.bskyb.inttest.adsmart.selenium;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by I&T Lab User on 09/01/2015.
 */
public class CustomHtmlUnitDriver {
    HtmlUnitDriver driver;
    String url;

    public CustomHtmlUnitDriver(){
        driver=new HtmlUnitDriver();
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void openUrl(){
        driver.get(url);
        System.out.println(driver.getTitle());
    }

    public void getPageSource(){
        System.out.println(driver.getPageSource());
    }

    public void destory(){
        driver.close();
    }

}
