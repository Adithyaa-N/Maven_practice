package com.SampleTestNG;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginPageTest extends Base{
    LoginPage lp;
    @BeforeClass
    public void Setup(){
        super.setup();
    }
    @BeforeMethod
    public void methodSetup(){
        lp = new LoginPage(driver);
    }
    @Test
    public void CheckUsername(){
        Assert.assertTrue(lp.checkusernameEnabled());
    }
    @Test
    public void testLoginPage(){
        lp.UserName("Alex");
        lp.PasswordName("Pa$$word123");
        lp.SubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String sucess = alert.getText();
        alert.accept();
        Assert.assertTrue(sucess.contains("successful"),"Expected success alert was not found.");
    }
    @Test
    public void testUsernameFailure(){
        lp.UserName("Alexx");
        lp.PasswordName("Pa$$word123");
        lp.SubmitBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String message = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("errorMsg")))).getText();
        Assert.assertTrue(message.contains("Invalid"),"Failure Test Successful");
    }
    @AfterMethod
    public void methodTeardown(){
        lp = null;
    }
    @AfterClass
    public void closeDown(){
        teardown();
    }
}
