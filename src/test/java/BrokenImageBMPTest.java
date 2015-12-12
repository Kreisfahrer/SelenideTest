import core.BmpTestBase;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by saap_by on 12.12.2015.
 */
public class BrokenImageBMPTest extends BmpTestBase {
    private ProxyServer bmp;

    @BeforeMethod
    public void setup() {
        bmp = getServer();
        bmp.newHar("broken-image");
        open("broken_images");
    }
}
