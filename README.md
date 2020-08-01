# SeleniumFourLearning

This repository is for Learning Selenium 4 in comparison to Selenium 3 particularly Selenium Grid

Command to start selenium Grid

java -jar selenium-server-4.0.0-alpha-6.jar standalone

IMPORTANT: 

Starting Selenium Grid with 3.141.59 for CHROMIUM EDGE use following commands

HUB - java -jar selenium-server-3.141.59.jar -role hub

NODE - java -Dwebdriver.edge.driver="G:\BrowserDrivers\msedgedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register/-port4466 -nodeConfig g:\BrowserDrivers\nodeconfig.json

NODE CONFIG JSON FOR EDGE CHROMIUM
{
  "capabilities":
  [
    {
      "browserName": "MicrosoftEdge",
      "maxInstances": 1,
      "version": "84.0.522.50",
      "seleniumProtocol": "WebDriver"
    }
  ],
  "hubHost": "localhost",
  "hubPort": 4444,
  "cleanUpCycle": 2000,
  "timeout": 30000,
  "proxy": "org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
  "maxSession": 1,
  "register": true,
  "registerCycle": 5000,
  "unregisterIfStillDownAfter": 10000,
  "debug": false,
  "role": "node",
  "acceptInsecureCerts":false
}