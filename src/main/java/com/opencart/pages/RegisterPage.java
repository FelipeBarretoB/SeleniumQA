package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }


    private By getFormField(String fieldName) {
        return By.xpath("//div[@id=\"content\"]/form//input[@name='" + fieldName + "']");
    }

    private By getFormCheckbox() {
        return By.xpath("//div[@id='content']/form//input[@name='agree']");
    }

    private By getFormSubmitButton() {
        return By.xpath("//div[@id='content']/form//input[@type='submit']");
    }

    public void fillFormField(String[] fields, String[] values) {
        for (int i = 0; i < fields.length; i++) {
            WebElement input = driver.findElement(getFormField(fields[i]));
            input.sendKeys(values[i]);
        }
    }

    public void submitForm() {
        WebElement submitButton = driver.findElement(By.xpath("//div[@id=\"content\"]/form//input[@type='submit']"));
        submitButton.click();
    }

    public void agreeToTerms() {
        WebElement agreeCheckbox = driver.findElement(getFormCheckbox());
        if (!agreeCheckbox.isSelected()) {
            agreeCheckbox.click();
        }
    }

    public void clickRegisterButton() {
        WebElement registerButton = driver.findElement(getFormSubmitButton());
        registerButton.click();
    }

}