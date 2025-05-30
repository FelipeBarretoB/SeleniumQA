package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By getItem(String item) {
        return By.xpath("//div[@id=\"content\"]//a[normalize-space(text())=\""+ item +"\"]");
    }

    private By getCheckoutButton() {
        return By.xpath("//div[@id='content']//div[@class='pull-right']//a");
    }

    public String chekItemInCart(String item) {
        return driver.findElement(getItem(item)).getText();
    }

    public void clickCheckoutButton() {
        driver.findElement(getCheckoutButton()).click();
    }




}
