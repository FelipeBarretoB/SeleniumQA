package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage{

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    //div[@class='form-group']/input[@name='email']
    private By getInputs(String inputName) {
        return By.xpath("//div[@class='form-group']/input[@name=\""+inputName+"\"]");
    }

    private By getLoginButton() {
        return By.xpath("//div[@class='well']//input[@value='Login']");
    }

    private By getAlertMessage() {
        return By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    }

    public void logIn(String email, String password) {
        driver.findElement(getInputs("email")).sendKeys(email);
        driver.findElement(getInputs("password")).sendKeys(password);
        driver.findElement(getLoginButton()).click();
    }

    public String getAlertMessageText() {
        return driver.findElement(getAlertMessage()).getText();
    }


}
