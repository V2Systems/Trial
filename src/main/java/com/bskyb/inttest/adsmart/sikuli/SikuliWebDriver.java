package com.bskyb.inttest.adsmart.sikuli;


import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.*;
import org.sikuli.webdriver.ImageElement;
import org.sikuli.webdriver.SikuliFirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by I&T Lab User on 12/01/2015.
 */
public class SikuliWebDriver {
    SikuliFirefoxDriver driver;
    String url;
    ImageElement image;

    public SikuliWebDriver(){

        driver=new SikuliFirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void openUrl(){
        driver.get(url);
        driver.getPageSource();
        System.out.println(driver.getTitle());
    }

    public void getPageSource(){
        System.out.println(driver.getPageSource());
    }

    public void destroy(){
        driver.close();
    }

    public void maximize(){
        driver.manage().window().maximize();
    }

    public void driverWait(int seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void findImageAndSendText(String imagePath, String Text) {

        try {

            image = driver.findImageElement(new File(imagePath).toURI().toURL());

            Mouse mouse = new DesktopMouse();
            Keyboard keyboard = new DesktopKeyboard();

            driver.switchTo().window(driver.getWindowHandle());
            ScreenRegion s = new DesktopScreenRegion();
            Target target = new ImageTarget(new File(imagePath));
            ScreenRegion r = s.find(target);
            ScreenPainter canvas = new ScreenPainter();

            canvas.box(r, 15);
            mouse.click(r.getCenter());
            keyboard.type(Text);


        } catch (Exception e) {
            System.out.println("couldn't Find Image:" + imagePath);
        }
    }

    public void findImageDblClickAndSendText(String imagePath, String Text) {

        try {

            image = driver.findImageElement(new File(imagePath).toURI().toURL());

            Mouse mouse = new DesktopMouse();
            Keyboard keyboard = new DesktopKeyboard();

            driver.switchTo().window(driver.getWindowHandle());
            ScreenRegion s = new DesktopScreenRegion();
            Target target = new ImageTarget(new File(imagePath));
            ScreenRegion r = s.find(target);
            ScreenPainter canvas = new ScreenPainter();

            canvas.box(r, 15);
            mouse.doubleClick(r.getCenter());
            keyboard.type(Text);


        } catch (Exception e) {
            System.out.println("couldn't Find Image:" + imagePath);
        }
    }

    public void findImageAndClick(String imagePath){

        try{

            image = driver.findImageElement(new File(imagePath).toURI().toURL());

            Mouse mouse = new DesktopMouse();
            ScreenRegion s = new DesktopScreenRegion();
            Target target = new ImageTarget(new File(imagePath));
            driver.switchTo().window(driver.getWindowHandle());
            ScreenRegion r = s.find(target);
            ScreenPainter canvas = new ScreenPainter();
            canvas.box(s,100);
            canvas.box(r,15);

            mouse.click(r.getCenter());

        }catch(Exception e){
            System.out.println("couldn't Find Image:" + imagePath);
        }


    }

}
