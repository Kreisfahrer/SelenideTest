import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FactoryRegistrationPage;
import pages.StaticBrokenImagePage;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by saap_by on 11.12.2015.
 */
public class BrokenImageBMPTest {
    private StaticBrokenImagePage staticBrokenImagePage;
    private ProxyServer bmp;

    @BeforeMethod
    public void setup() {
        staticBrokenImagePage = new StaticBrokenImagePage();
        open(staticBrokenImagePage.getPathLinkUrl());
    }

    @Test
    public void brokenImageTest() {
        staticBrokenImagePage.imagesLoadedTest();
    }

}
