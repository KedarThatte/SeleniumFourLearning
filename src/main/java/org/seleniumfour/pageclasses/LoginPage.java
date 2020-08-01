package org.seleniumfour.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author kedar thatte
 */
public class LoginPage {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy( xpath = ".//input[@id='loginpw']")
    WebElement passwordinput;

    @FindBy ( xpath = ".//button[@type='submit']")
    WebElement loginbtn;

    public LoginPage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,10);
    }
    public void enterPassword()throws Exception{
        wait.until(ExpectedConditions.visibilityOf(passwordinput)).isEnabled();
        wait.until(ExpectedConditions.visibilityOf(passwordinput)).sendKeys("welcome");
        wait.until(ExpectedConditions.visibilityOf(loginbtn)).click();
    }
}
