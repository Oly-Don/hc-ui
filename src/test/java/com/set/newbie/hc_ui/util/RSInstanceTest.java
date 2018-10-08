package com.set.newbie.hc_ui.util;

import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class RSInstanceTest {

    final String TEST_URL = "http://www.baidu.com";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(timeOut = 180000)
    public void testGetRemoteInstarnce() throws MalformedURLException, InterruptedException {
        driver = RSInstance.getRemoteInstarnce();
        Assert.assertNotEquals(driver, null);
        driver.get(TEST_URL);
        System.out.println(driver.getPageSource());
        Assert.assertEquals(driver.findElement(By.id("su")).getAttribute("value"), "百度一下");
    }

    @AfterMethod
    public void destroy() throws IOException {
        if (driver != null) {
            ScreenShoterUtil.screenShot(driver, "screenshot.png");
            driver.quit();
        }
    }
}