package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.net.URL;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.*;

public class TestBase {
    protected final static String DEFAULT_URL = "http://the-internet.herokuapp.com/";
    protected Properties environment;

    @BeforeTest
    public void configure() throws Exception {
        String cloud = System.getProperty("browserStack", "");
        if (cloud.length() > 0) {
            String[] creds = cloud.split(" ");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", System.getProperty("browser", "chrome"));
            caps.setCapability("browser_version", System.getProperty("browserVersion", "42"));
            caps.setCapability("os", System.getProperty("OS", "OS X"));
            caps.setCapability("os_version", System.getProperty("OS_version", "Yosemite"));
            String hubUrl = "http://" + creds[0] + ":" + creds[1] + "@hub.browserstack.com/wd/hub";
            WebDriver driver = new RemoteWebDriver(new URL(hubUrl), caps);
            WebDriverRunner.setWebDriver(driver);
        }

        Configuration.timeout = 10000;
        Configuration.baseUrl = System.getProperty("baseUrl", DEFAULT_URL);
        //Configuration.browser = WebDriverRunner.HTMLUNIT;
        getEnvironmentProperties();
    }

    @AfterTest
    public void cleanup() {
        saveEnvironment();
    }

    protected void getEnvironmentProperties() {
        environment = new Properties();
        if(!isHtmlUnit()) {
            Capabilities caps = ((RemoteWebDriver) getWebDriver()).getCapabilities();
            environment.put("browser", caps.getBrowserName());
            environment.put("browser.version", caps.getVersion());
            environment.put("platform", caps.getPlatform().name());
            environment.put("platform.version", caps.getPlatform().getMajorVersion() + "." +
                    caps.getPlatform().getMinorVersion());
            environment.put("url", Configuration.baseUrl);
            environment.put("OS", System.getProperty("os.name"));
            environment.put("OS.version", System.getProperty("os.version"));
        } else {
            environment.put("browser", "htmlunit");
        }
    }

    protected void saveEnvironment() {
        File resultsFolder = new File("./target/allure-results");
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }
        try (OutputStream out = new FileOutputStream("./target/allure-results/environment.properties")){
            environment.store(out, "Allure environment properties");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
