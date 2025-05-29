package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String ObtenerTituloPagina() {
        return driver.getTitle();
    }

    private By product(String product){
        return By.xpath("//a[contains(text(),'"+product+"')]");
    }

    private By addProductButton(){
        return By.xpath("//div[@id='product']//button[@id='button-cart']");
    }

    public void selectProduct(String product){
        wait.until(ExpectedConditions.visibilityOfElementLocated(product(product)));
        driver.findElement(product(product)).click();
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addProductButton()));
        driver.findElement(addProductButton()).click();
    }
}
