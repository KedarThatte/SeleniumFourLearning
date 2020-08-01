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
public class HomePage {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy (xpath = ".//div[@id='total_queries']")
    WebElement totalquerieswidget;

    public HomePage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,10);
    }
    public boolean userIsInPiHoleHomePage()throws Exception{
        wait.until(ExpectedConditions.visibilityOf(totalquerieswidget)).isDisplayed();
        System.out.println("User is on Pi-Hole HomePage after successful Login");
        return true;
    }

}
