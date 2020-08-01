package org.seleniumfour.core;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.seleniumfour.config.Config;
import org.seleniumfour.pageclasses.NavigationPane;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.seleniumfour.listeners.TestListener.saveTextLog;

/**
 * @author kedar thatte
 */
public class BaseDriver extends Config {

    public RemoteWebDriver driver;
    public String URL ,Node;
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
    public String parentwindowhandle;

    public static RemoteWebDriver getDriver() {
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }

    @BeforeClass
    @Parameters({"browser"})
    @Severity(SeverityLevel.CRITICAL)
    @Step
    @Description("Opens the AUT")
    public RemoteWebDriver launchApplication(String browser) throws IOException {
        System.out.println(config.getProperty("usersite"));
        System.out.println(config.getProperty("adminsite"));

        Node="http://localhost:4444/wd/hub/";
        //Node = "http://ondemand.eu-central-1.saucelabs.com:80/wd/hub";
        //DesiredCapabilities cap=null;

        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browser","chrome");
            options.setCapability("acceptSslCerts", "true");
            options.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
            /*options.addArguments("--incognito");
            options.addArguments("--headless");*/
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("firefox")){
            FirefoxOptions options= new FirefoxOptions();
            options.setCapability("browser","firefox");

//            options.addArguments("inPrivate","true");
//            options.setHeadless(true);
            options.setAcceptInsecureCerts(true);
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("opera")){
            OperaOptions options = new OperaOptions();
            //options.setCapability("browser","opera");
            options.setCapability("browserName","operablink");
            //options.setCapability("platform","MAC");
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if (browser.equalsIgnoreCase("safari")){
            SafariOptions options=new SafariOptions();
            options.setCapability("browser","safari");
            //options.setCapability("technologyPreview","true");
            options.setCapability("platform","MAC");
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("Edge")){
            EdgeOptions options = new EdgeOptions();
            System.setProperty("java.net.useSystemProxies","true");
            options.setCapability("browser","MicrosoftEdge");
            options.setCapability("browser_version","84.0.522.50");
            options.setCapability("platform","WINDOWS");
            options.setCapability("resolution", "1920x1080");
            /*options.setCapability("userChromium","true");
            options.addArguments("-inprivate");
            options.addArguments("headless");*/

            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("ie")){
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability("browser","ie");

            options.setCapability("platform","WINDOWS");
            driver=new RemoteWebDriver(new URL(Node),options);
        }
        setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(config.getProperty("auturl"));
        NavigationPane navigationPane = new NavigationPane(driver);
        Assert.assertTrue(navigationPane.navigationPaneOpen());
        saveTextLog(driver.getCurrentUrl());
        parentwindowhandle=driver.getWindowHandle();
        System.out.println("AUT is Open in "+browser+" browser");
        return driver;
    }
    @AfterClass
    public void tearDown(){
        /*driver.findElement(By.xpath(".//span[text()='Logout']")).click();*/
        driver.findElement(By.xpath(".//span[text()='Login']")).isDisplayed();
        //driver.close();
        driver.quit();
    }

    public void windowswitchback(){
        driver.switchTo().window(parentwindowhandle);
    }
    public void windowSwitch(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

    }
    public void haltscript(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
