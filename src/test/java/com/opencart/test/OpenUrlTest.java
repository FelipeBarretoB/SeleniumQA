package com.opencart.test;

import com.opencart.pages.BasePage;
import org.junit.jupiter.api.Test;
import com.opencart.utils.Constants;

public class OpenUrlTest extends BaseTest{

    @Test
    public void openUrl(){
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.URL);

    }
}
