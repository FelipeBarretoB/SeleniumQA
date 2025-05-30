package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPlacedPage extends BasePage{
    public OrderPlacedPage(WebDriver driver) {
        super(driver);
    }

    private By getConfirmMessage() {
        return By.xpath("//div[@id='content']/h1");
    }

    public String getConfirmMessageText() {
        return driver.findElement(getConfirmMessage()).getText();
    }
}
