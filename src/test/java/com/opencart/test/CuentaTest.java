package com.opencart.test;
import com.opencart.pages.*;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CuentaTest extends BaseTest {

    //tenemos que agregar el random para que no se repita el email
    int randomInt = Constants.RANDOM_INT;

    String[] expectedFields = {
            "firstname", "lastname", "email", "telephone", "password", "confirm"
    };

    String expectedMessageCreate = "Congratulations! Your new account has been successfully created!";

    String expectedMessageLog = "My Account";

    String[] values = {
            "John", "Doe", "email@mail.com" + randomInt, "1234567890", "password123", "password123"
    };

    @Test
    @Order(1)
    public void registrarCuenta() {
        openUrl();

        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);

        homePage.clickRegisterButton();

        registerPage.fillFormField(expectedFields, this.values);
        registerPage.agreeToTerms();
        registerPage.clickRegisterButton();

        Assertions.assertEquals(expectedMessageCreate, accountCreationPage.getCongratulationMessageText(), "The page title is not as expected");
        homePage.clickLogoutButton();

    }

    @Test
    @Order(2)
    public void registrarCuentaConExcel() {
        openUrl();
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        List<String[]> data = excel.readData(1);
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        data.forEach(dataprod -> {
            dataprod[2] = dataprod[2] + randomInt; // Append random integer to email to avoid duplicates
            homePage.clickRegisterButton();
            registerPage.fillFormField(expectedFields, dataprod);
            registerPage.agreeToTerms();
            registerPage.clickRegisterButton();
            Assertions.assertEquals(expectedMessageCreate, accountCreationPage.getCongratulationMessageText(), "The page title is not as expected");
            homePage.clickLogoutButton();
        });

    }

    @Test
    @Order(3)
    public void ingresarSesion() {
        openUrl();
        HomePage homePage = new HomePage(driver);
        LogInPage logInPage= new LogInPage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);
        homePage.clickLogInButton();
        logInPage.logIn(this.values[2], this.values[4]);
        Assertions.assertEquals(expectedMessageLog, loggedInPage.getWelcomeMessageText(), "The page title is not as expected");

    }

    @Test
    @Order(4)
    public void ingresarSesionConExcel() {
        openUrl();
        Excel excel = new Excel(Constants.FILE_PATH_EXCEL);
        List<String[]> data = excel.readData(1);
        HomePage homePage = new HomePage(driver);
        LogInPage logInPage= new LogInPage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);
        data.forEach(dataprod -> {
            homePage.clickLogInButton();
            logInPage.logIn(dataprod[2]+ randomInt, dataprod[4]);
            Assertions.assertEquals(expectedMessageLog, loggedInPage.getWelcomeMessageText(), "The page title is not as expected");
            homePage.clickLogoutButton();
        });
    }
}
