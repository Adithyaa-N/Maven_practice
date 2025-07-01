package com.SampleTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class Base {
    protected WebDriver driver;
    public void setup()
    {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\2407244\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("C:/Users/2407244/Downloads/login.html");
    }
    public void teardown(){
        if (driver != null) {
            driver.quit();
        }
    }

}
