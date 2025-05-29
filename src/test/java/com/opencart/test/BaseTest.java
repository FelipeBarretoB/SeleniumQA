package com.opencart.test;

import com.opencart.pages.BasePage;
import com.opencart.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        //configurar el driver
        WebDriverManager.chromedriver().setup();

        //crear instancia de webdriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterEach
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    public void openUrl() {
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.URL);

    }
}
