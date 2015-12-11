import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import core.BmpTestBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Helpers.getUrls;
import static helpers.Helpers.mapToString;

/**
 * Created by saap_by on 11.12.2015.
 */
public class BrokenImageBMPTest extends BmpTestBase {
    private ProxyServer bmp;

    @BeforeMethod
    public void setup() {
        bmp = getServer();
        bmp.newHar("broken-image");
        open("broken_images");
    }

    @Test
    public void brokenImageTest() {
        List<String> links = getUrls($$(".example>img"), "src");
        Map<String, String> responseCode = new HashMap<>();
        Har har = bmp.getHar();
        for(String link : links) {
            for (HarEntry entry : har.getLog().getEntries()) {
                if (entry.getRequest().getUrl().equals(link)) {
                    if (entry.getResponse().getStatus() >= 400) {
                        responseCode.put(link, String.valueOf(entry.getResponse().getStatus()));
                    }
                }
            }
        }
        Assert.assertEquals(responseCode.size(), 0, mapToString(responseCode));
    }
}
