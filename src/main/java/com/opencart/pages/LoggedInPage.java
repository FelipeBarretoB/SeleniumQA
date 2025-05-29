package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInPage extends HomePage {
    public LoggedInPage(WebDriver driver) {
        super(driver);
    }

    private By getWelcomeMessage() {
        return By.xpath("//div[@id='content']/h2[contains(text(), 'My Account')]");
    }

    public String getWelcomeMessageText() {
        return driver.findElement(getWelcomeMessage()).getText();
    }
}
