package com.opencart.test;


import com.opencart.pages.BasePage;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductsPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import com.opencart.utils.Verify;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComprarTest extends BaseTest{

    @Test
    public void openUrl(){
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.URL);
        HomePage homePage = new HomePage(driver);
        ProductsPage productPage = new ProductsPage(driver);




        homePage.selectCategory("Components");
        homePage.selectSubCategory("Monitors");

        Verify.verify(() -> assertThat(productPage.ObtenerTituloPagina()).contains("o"));

        Assertions.assertEquals("Monitors", productPage.ObtenerTituloPagina(), "The page title is not as expected");

        productPage.selectProduct("Apple Cinema");

        Verify.verifyAll();
    }
}
