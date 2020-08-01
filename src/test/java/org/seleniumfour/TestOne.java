package org.seleniumfour;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.seleniumfour.core.BaseDriver;
import org.seleniumfour.listeners.TestListener;
import org.seleniumfour.pageclasses.HomePage;
import org.seleniumfour.pageclasses.NavigationPane;
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
    @Severity(SeverityLevel.CRITICAL)
    public void logintoAUT()throws Exception{
        driver.findElement(By.xpath(".//span[text()='Login']")).isDisplayed();
        NavigationPane navigationPane = new NavigationPane(driver);
        navigationPane.loginPiHole();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.userIsInPiHoleHomePage());
        System.out.println("User Logged in Successfully");
        saveTextLog("User Logged in Successfully");

    }
    @Test(dependsOnMethods = "logintoAUT",description = "To verify on failure script captures screeshot")
    @Description("To verify on failure script captures screenshot")
    public void failureScreenShotTest(){
        driver.findElement(By.xpath(".//span[text()='Logout']")).click();
        System.out.println("Emulating Test Failure");
        Assert.assertTrue(driver.findElement(By.xpath(".//span[text()='Login']")).isDisplayed());

    }


}
