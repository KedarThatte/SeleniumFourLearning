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
public class NavigationPane {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy (xpath = ".//span[text()='Login']")
    WebElement loginbtn;

    @FindBy ( xpath = ".//span[text()='Logout']")
    WebElement logoutbtn;

    public NavigationPane (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,10);
    }

    public boolean navigationPaneOpen(){
        wait.until(ExpectedConditions.visibilityOf(loginbtn)).isDisplayed();
        return true;
    }

    public void loginPiHole()throws Exception{
        wait.until(ExpectedConditions.visibilityOf(loginbtn)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(loginbtn)).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword();

    }


}
