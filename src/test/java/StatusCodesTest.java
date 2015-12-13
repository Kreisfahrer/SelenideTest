import core.BmpTestBase;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Helpers.getUrls;

public class StatusCodesTest extends BmpTestBase{

    @BeforeMethod
    public void setup(){
        open("status_codes");

    }

    @Test
    public void responseCodeTest(){
        List<String> links = getUrls($$(".example>ul>li>a"), "href");
        Map<String, String> responseCode = new HashMap<>();
        for (String link : links) {
            server.newHar("the-internet");
            open(link);
            Har har = server.getHar();
            for (HarEntry entry : har.getLog().getEntries()) {
                if (entry.getResponse().getStatus() >= 400){
                    responseCode.put(link, String.valueOf(entry.getResponse().getStatus()));
                }
            }
        }
        Assert.assertEquals(responseCode.size(), 0, mapToString(responseCode));
    }

    private String mapToString(Map<String, String> map) {
        StringBuilder message = new StringBuilder();
        for(String key : map.keySet()) {
            message.append(String.format("\nurl: %s, response code: %s", key, map.get(key)));
        }
        return message.toString();
    }

}
