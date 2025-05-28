package com.opencart.test;
import com.opencart.pages.*;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

public class RegistrarCuentaTest extends BaseTest{

    //tenemos que agregar el random para que no se repita el email
    int randomInt = Constants.RANDOM_INT;

    String[] expectedFields = {
        "firstname", "lastname", "email", "telephone", "password", "confirm"
    };

    String expectedMessage = "Congratulations! Your new account has been successfully created!";



    public void openUrl() {
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.URL);

    }

    @Test
    public void registrarCuenta() {
        openUrl();

        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);

        homePage.clickRegisterButton();


        String[] values = {
            "John", "Doe", "email@mail.com"+randomInt, "1234567890", "password123", "password123"
        };
        registerPage.fillFormField(expectedFields, values);
        registerPage.agreeToTerms();
        registerPage.clickRegisterButton();

        Assertions.assertEquals(expectedMessage, accountCreationPage.getCongratulationMessageText(), "The page title is not as expected");
        homePage.clickLogoutButton();

    }

    @Test
    public void registrarCuentaConExcel(){
        openUrl();
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        List<String[]> data = excel.readData(1);
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        data.forEach(dataprod->{
            homePage.clickRegisterButton();
            registerPage.fillFormField(expectedFields, dataprod);
            registerPage.agreeToTerms();
            registerPage.clickRegisterButton();
            Assertions.assertEquals(expectedMessage, accountCreationPage.getCongratulationMessageText(), "The page title is not as expected");
            homePage.clickLogoutButton();
        });

    }
}
