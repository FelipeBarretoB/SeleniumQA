package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By guestAccountRadioButton() {
        return By.xpath("//div[@class='row']//input[@value='guest']");
    }

    private By continueButton() {
        return By.xpath("//div[@id=\"collapse-checkout-option\"]//input[@value='Continue']");
    }

    private By getAccountInputs(String inputName) {
        return By.xpath("//div[@class='panel-body']//fieldset[@id = 'account']//input[@name='" + inputName + "']");
    }

    private By getAddressInputs(String inputName) {
        return By.xpath("//div[@class='panel-body']//fieldset[@id='address']//input[@name='" + inputName + "']");
    }

    //div[@class='panel-body']//fieldset[@id='address']//select[@name="country_id"]/option[@value='222']
    //div[@class='panel-body']//fieldset[@id='address']//select[@name='country_id']/option[@value='222']
    private By getCityInput(String inputName, String value) {
        return By.xpath("//fieldset[@id='address']//select[@name='"+ inputName +"']/option[@value='"+value+"']");
    }

    private By getBillingContinueButton() {
        return By.xpath("//div[@class='panel-body']/div[@class='buttons']//input[@id='button-guest']");
    }

    private By getDeliveryMethodContinueButton() {
        return By.xpath("//div[@class='panel-body']/div[@class='buttons']//input[@id='button-shipping-method']");
    }

    private By getPaymentMethodContinueButton() {
        return By.xpath("//div[@class='panel-body']/div[@class='buttons']//input[@id='button-payment-method']");
    }

    private By agreementCheckbox() {
        return By.xpath("//div[@class='panel-body']/div[@class='buttons']//input[@name='agree']");
    }

    private By getConfirmOrderButton() {
        return By.xpath("//div[@class='panel-body']/div[@class='buttons']//input[@id='button-confirm']");
    }



    public void selectGuestAccount() {
        driver.findElement(guestAccountRadioButton()).click();
        driver.findElement(continueButton()).click();
    }

    public void fillAccountsForms(String[] Account, String[] values){
        for (int i = 0; i < Account.length; i++) {
            driver.findElement(getAccountInputs(Account[i])).sendKeys(values[i]);
        }
    }

    public void fillAddressForms(String[] Address, String[] values) {
        for (int i = 0; i < Address.length; i++) {
            driver.findElement(getAddressInputs(Address[i])).sendKeys(values[i]);
        }
    }

    public void selectCity(String[] inputName, String[] value) {
        for (int i = 0; i < inputName.length; i++) {
            driver.findElement(getCityInput(inputName[i], value[i])).click();
        }
    }

    public void clickBillingContinueButton() {
        driver.findElement(getBillingContinueButton()).click();
    }

    public void clickDeliveryMethodContinueButton() {
        driver.findElement(getDeliveryMethodContinueButton()).click();
    }

    public void clickPaymentMethodContinueButton() {
        driver.findElement(agreementCheckbox()).click();
        driver.findElement(getPaymentMethodContinueButton()).click();
    }

    public void clickConfirmOrder() {
        driver.findElement(getConfirmOrderButton()).click();
        try {
            Thread.sleep(500); // es demasiado rapido y no deja cargar la siguiente pagina
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



}
