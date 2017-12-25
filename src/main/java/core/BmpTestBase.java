package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import static org.openqa.selenium.net.PortProber.findFreePort;

public class BmpTestBase extends TestBase{

    protected ProxyServer server;

    @Override
    @BeforeTest
    public void configure() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = System.getProperty("baseUrl", DEFAULT_URL);
        server = new ProxyServer(findFreePort());

        try {
            server.start();
            WebDriverRunner.setProxy(server.seleniumProxy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Configuration.browser = WebDriverRunner.HTMLUNIT;

        getEnvironmentProperties();
    }

    @AfterTest
       public void proxyShutDown() throws Exception {
        if (server != null) {
            server.stop();
        }
        WebDriverRunner.setProxy(null);
    }

    @AfterTest
    public void cleanup() {
        saveEnvironment();
    }

}
