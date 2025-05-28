package com.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By category(String category) {
        return By.xpath("//ul[@class=\"nav navbar-nav\"]/li/a[normalize-space(text())=\""+ category +"\"]");
    }

    private By subCategory(String subCategory) {
        return By.xpath("//ul[@class=\"nav navbar-nav\"]/li//a[contains(normalize-space(), \""+ subCategory +"\")]");

    }

    private By userIcon() {
        return By.xpath("//div[@id=\"top-links\"]//a[@title=\"My Account\"]");
    }

    private By registerButton() {
        return By.xpath("//div[@id=\"top-links\"]//a[normalize-space(text())=\"Register\"]");
    }

    private By logoutButton() {
        return By.xpath("//div[@id=\"top-links\"]//a[normalize-space(text())=\"Logout\"]");
    }

    public void clickRegisterButton() {
        driver.findElement(userIcon()).click();
        driver.findElement(registerButton()).click();
    }

    public void clickLogoutButton() {
        driver.findElement(userIcon()).click();
        driver.findElement(logoutButton()).click();
    }

    public void selectCategory(String category) {
        driver.findElement(category(category)).click();
    }

    public void selectSubCategory(String subCategory) {
        Wait<WebDriver> waitf = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchFieldError.class);
        WebElement subCat = waitf.until(driver -> driver.findElement(subCategory(subCategory)));
        subCat.click();
    }
}
