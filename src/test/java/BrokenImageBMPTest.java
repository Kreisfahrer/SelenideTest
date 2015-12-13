import core.BmpTestBase;
import net.lightbody.bmp.core.har.*;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Helpers.getUrls;
import static helpers.Helpers.mapToString;

/**
 * Created by saap_by on 12.12.2015.
 */
public class BrokenImageBMPTest extends BmpTestBase {
    private Har har;
    private String url;

    @BeforeMethod
    public void setup() {
        server.newHar("broken-image");
        open("broken_images");
    }

    @Test
    public void brokenImageTest() {
        har = server.getHar();
        List<String> links = getUrls($$(".example>img"), "src");
        Map<String, String> brokenImage = new HashMap<>();
        for (HarEntry entry : har.getLog().getEntries()) {
            url = entry.getRequest().getUrl();
            int responseCode = entry.getResponse().getStatus();
            for(String link : links) {
                if (url.equals(link)) {
                    if (responseCode >= 400) {
                        brokenImage.put(entry.getRequest().getUrl(), String.valueOf(responseCode));
                    }
                }
            }
        }
        Assert.assertEquals(brokenImage.size(), 0, mapToString(brokenImage));
    }
}
