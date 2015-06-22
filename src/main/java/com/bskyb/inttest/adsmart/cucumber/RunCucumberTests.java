package com.bskyb.inttest.adsmart.cucumber;

/**
 * Created by I&T Lab User on 16/12/2014.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        //monochrome = true,
        format = { "pretty","html:cucumber-html-reports", "json:cucumber-html-reports/cucumber.json", "junit:cucumber-html-reports/cucumber-junit.xml"},
        glue = "com.bskyb.inttest.adsmart.cucumber.glue.stepdefinitations",
        //features = "target\\classes\\com\\bskyb\\inttest\\adsmart\\cucumber\\"
        features = "target\\classes\\com\\bskyb\\inttest\\adsmart\\cucumber\\features"
        //features = "target\\classes\\com\\bskyb\\inttest\\adsmart\\cucumber\\standalone"
        //features = "target\\classes\\com\\bskyb\\inttest\\adsmart\\cucumber\\STB_AdSmart_Campaigns.feature"
        ,tags ="@standaloneTest"
)

public class RunCucumberTests {

}
