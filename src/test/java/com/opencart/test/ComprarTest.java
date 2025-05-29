package com.opencart.test;



import com.opencart.pages.BasePage;
import com.opencart.pages.CartPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductsPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import com.opencart.utils.Verify;


import java.util.ArrayList;
import java.util.List;

public class ComprarTest extends BaseTest{

    List<String[]> data2 = new ArrayList<>();

    @Test
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
    public void comprarTestExcel(){
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.URL);
        HomePage homePage = new HomePage(driver);
        ProductsPage productPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
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
        Verify.verifyAll();
    }
}
