package org.seleniumfour.config;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Properties;

/**
 * @author kedar thatte
 */
public class Config {

    public static Properties config;



    private String testCaseName;
    private long testStartTime;
    private long testFinishTime;
    private long testDurationTime;
    private String testFailedMsg;

    protected long getTestStartTime() {
        return testStartTime;
    }

    protected void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }

    protected long getTestFinishTime() {
        return testFinishTime;
    }

    protected void setTestFinishTime(long testFinishTime) {
        this.testFinishTime = testFinishTime;
    }

    protected long getTestDurationTime() {
        return testDurationTime;
    }

    protected void setTestDurationTime(long testDurationTime) {
        this.testDurationTime = testDurationTime;
    }

    protected String getTestCaseName()
    {
        return testCaseName;
    }

    protected void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestFailedMsg() {
        return testFailedMsg;
    }

    public void setTestFailedMsg(String testFailedMsg) {
        this.testFailedMsg = testFailedMsg;
    }

    /*public void setLocalBrowserDrivers(String browser){
        String path = (System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"drivers");
        File file = new File(path+File.separator+browser+"driver");
        System.out.println(file.getAbsolutePath());
        System.setProperty("webdriver."+browser+".driver",file.getAbsolutePath());
    }*/

    @BeforeSuite
    @Step
    @Description("Loads Test Data for a Suite")
    public void loadConfig() throws IOException {
        config= new Properties();
        config.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        System.out.println("Config loaded!");
        /*setLocalBrowserDrivers("chrome");
        setLocalBrowserDrivers("gecko");*/
    }

}
