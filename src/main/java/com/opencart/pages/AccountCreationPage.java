package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreationPage extends HomePage{

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    private By getCongratulationMessage() {
        return By.xpath("//div[@id='content']//p[contains(text(), 'Congratulations')]");
    }

    public String getCongratulationMessageText() {
        return driver.findElement(getCongratulationMessage()).getText();
    }
}
