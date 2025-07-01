package com.SampleTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver){
    super();
    this.driver = driver;
    PageFactory.initElements(driver,this);
    }
    @FindBy(id="username")
    WebElement userNameFiled;
    @FindBy(id="password")
    WebElement passwordFiled;
    @FindBy(xpath="//button")
    WebElement submitBtn;

    public void UserName(String name){
        userNameFiled.sendKeys(name);
    }
    public void PasswordName(String password){
        passwordFiled.sendKeys(password);
    }
    public void SubmitBtn(){
        submitBtn.click();
    }
    public boolean checkusernameEnabled(){
        return userNameFiled.isEnabled();
    }

}
