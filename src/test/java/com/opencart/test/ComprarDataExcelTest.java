package com.opencart.test;

import com.opencart.pages.BasePage;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductsPage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import com.opencart.utils.Verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ComprarDataExcelTest extends BaseTest{

    @Test
    public void openUrl(){
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.URL);
        HomePage homePage = new HomePage(driver);
        ProductsPage productPage = new ProductsPage(driver);

        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        List<String[]> data = excel.readData(0);

        data.forEach(dataprod->{
            String category = dataprod[0];
            String subcategory = dataprod[1];
            String product = dataprod[2];
            homePage.selectCategory(category);
            if(!subcategory.isEmpty()){
                homePage.selectSubCategory(subcategory);
                Assertions.assertEquals(subcategory, productPage.ObtenerTituloPagina(), "The page title is not as expected");
            }else{
                Assertions.assertEquals(category, productPage.ObtenerTituloPagina(), "The page title is not as expected");
            }


        });

    }
}


