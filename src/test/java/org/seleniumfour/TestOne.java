package org.seleniumfour;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.seleniumfour.core.BaseDriver;
import org.seleniumfour.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.seleniumfour.listeners.TestListener.saveTextLog;

/**
 * @author kedar thatte
 */
@Listeners(TestListener.class)
public class TestOne extends BaseDriver {

    @Test(description = "To verify user is able to login to AUT")
    @Description("To verify user is able to login to AUT")
    public void logintoAUT(){

        driver.findElement(By.xpath(".//span[text()='Login']")).click();
        driver.findElement(By.xpath(".//input[@id='loginpw']")).isDisplayed();
        driver.findElement(By.xpath(".//input[@id='loginpw']")).sendKeys("welcome");
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Assert.assertTrue(driver.findElement(By.xpath(".//span[text()='Logout']")).isDisplayed());
        System.out.println("User Logged in Successfully");
        saveTextLog("User Logged in Successfully");

    }
    @Test(dependsOnMethods = "logintoAUT",description = "To verify on failure script captures screeshot")
    @Description("To verify on failure script captures screenshot")
    public void failureScreenShotTest(){
        System.out.println("Emulating Test Failure");
        Assert.assertTrue(driver.findElement(By.xpath(".//span[text()='Login']")).isDisplayed());

    }


}
