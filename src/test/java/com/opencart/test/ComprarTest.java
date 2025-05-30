package com.opencart.test;



import com.opencart.pages.*;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import com.opencart.utils.Verify;


import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComprarTest extends BaseTest{

    List<String[]> data2 = new ArrayList<>();

    @Test
    @Order(1)
    public void comprarTest(){
        openUrl();
        HomePage homePage = new HomePage(driver);
        ProductsPage productPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.selectCategory("Components");
        homePage.selectSubCategory("Monitors");
        Assertions.assertEquals("Monitors", productPage.ObtenerTituloPagina(), "The page title is not as expected");
        productPage.selectProduct("Samsung SyncMaster 941BW");
        productPage.addProductToCart();
        homePage.clickCart();
        try{
            cartPage.chekItemInCart("Samsung SyncMaster 941BW");
        }catch (org.openqa.selenium.NoSuchElementException e){
            Assertions.fail("The product is not in the cart: " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void comprarTestExcel(){
        openUrl();
        HomePage homePage = new HomePage(driver);
        ProductsPage productPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        OrderPlacedPage orderPlacedPage = new OrderPlacedPage(driver);
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        excel.setOutputPath(Constants.OUTPUT_FILE_PATH_EXCEL);
        List<String[]> data = excel.readData(0);


        data.forEach(dataprod->{
            String category = dataprod[0];
            String subcategory = dataprod[1];
            String product = dataprod[2];
            homePage.selectCategory(category);
            if(!subcategory.isEmpty()){
                homePage.selectSubCategory(subcategory);
                Verify.verify(() -> assertThat(productPage.ObtenerTituloPagina()).isEqualTo(subcategory));
            }else{
                Verify.verify(() -> assertThat(productPage.ObtenerTituloPagina()).isEqualTo(category));
            }
            productPage.selectProduct(product);
            productPage.addProductToCart();


        });
        homePage.clickCart();
        data.forEach(dataprod -> {
            String product = dataprod[2];
            Verify.verify(() -> {
                String[] itemStatus;
                try {
                    cartPage.chekItemInCart(product);
                    itemStatus = new String[]{product, "In Cart"};
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    Assertions.fail("The product is not in the cart: " + e.getMessage());
                    itemStatus = new String[]{product, "Not In Cart"};
                }
                data2.add(itemStatus);
            });
        });
        excel.writeData(data2, "Comprar test excel");

        cartPage.clickCheckoutButton();
        checkoutPage.selectGuestAccount();

        String[] accountForm = {
                "firstname","lastname","email","telephone"};
        String[] accountValues = {
                "Test","User","email@email.com","1234567890"};
        checkoutPage.fillAccountsForms(accountForm, accountValues);
        String[] addressForm = {
                "company","address_1","address_2","city","postcode"};
        String[] addressValues = {
                "Test Company","123 Test St","Suite 100","Test City","12345"};
        checkoutPage.fillAddressForms(addressForm, addressValues);
        String[] selectForm = {
                "country_id","zone_id"};
        String[] selectValues = {
                "222","3545"};
        checkoutPage.selectCity(selectForm,selectValues);
        checkoutPage.clickBillingContinueButton();
        checkoutPage.clickDeliveryMethodContinueButton();
        checkoutPage.clickPaymentMethodContinueButton();
        checkoutPage.clickConfirmOrder();
        Assertions.assertEquals("Your order has been placed!", orderPlacedPage.getConfirmMessageText(), "The confirmation message is not as expected");
        Verify.verifyAll();
    }

    @Test
    @Order(3)
    public void comprarTestExcelFail() {
        data2.clear();
        openUrl();
        HomePage homePage = new HomePage(driver);
        ProductsPage productPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        excel.setOutputPath(Constants.OUTPUT_FILE_PATH_EXCEL);
        List<String[]> data = excel.readData(2);

        data.forEach(dataprod -> {
            String category = dataprod[0];
            String subcategory = dataprod[1];
            String product = dataprod[2];
            homePage.selectCategory(category);
            if (!subcategory.isEmpty()) {
                homePage.selectSubCategory(subcategory);
                Verify.verify(() -> assertThat(productPage.ObtenerTituloPagina()).isEqualTo(subcategory));
            } else {
                Verify.verify(() -> assertThat(productPage.ObtenerTituloPagina()).isEqualTo(category));
            }
            Verify.verify(() -> {
                try {
                    productPage.selectProduct(product);
                    productPage.addProductToCart();
                } catch (org.openqa.selenium.TimeoutException e) {
                    Assertions.fail("Product not found or not visible: " + product);
                }
            });
        });

        homePage.clickCart();
        data.forEach(dataprod -> {
            String product = dataprod[2];
            Verify.verify(() -> {
                String[] itemStatus;
                try {
                    cartPage.chekItemInCart(product);
                    itemStatus = new String[]{product, "In Cart"};
                    data2.add(itemStatus);
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("alo?????");
                    itemStatus = new String[]{product, "Not In Cart"};
                    data2.add(itemStatus);
                    Assertions.fail("The product is not in the cart: " + e.getMessage());
                }

            });
        });

        // Write to Excel before assertions are thrown
        excel.writeData(data2, "Comprar test excel fail");
        //Verify.verifyAll();
    }
}
