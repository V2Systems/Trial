package com.bskyb.inttest.adsmart.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by I&T Lab User on 09/01/2015.
 */
public class CustomWebDriver {

    WebDriver driver;
    //FirefoxDriver driver;
    String url;

    public CustomWebDriver(String application){
        if(application.equalsIgnoreCase("FIREFOX"))
        {
            FirefoxProfile profile = new FirefoxProfile();
            driver=new FirefoxDriver();
        }
        if(application.equalsIgnoreCase("IE"))
        {
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

            File file = new File("C:\\Users\\I&T Lab User\\Downloads\\IEDriverServer_Win32_2.44.0\\IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            driver=new InternetExplorerDriver(caps);
        }

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

    public void destroy(){
        driver.close();
    }
}
