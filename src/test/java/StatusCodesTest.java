import core.BmpTestBase;
import net.lightbody.bmp.core.har.*;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.StatusCodesPage;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class StatusCodesTest extends BmpTestBase{

    @BeforeMethod
    public void setup(){
        open("status_codes");
    }

    @Test
    public void responseCodePageTest() {
        int countLinks = $$(StatusCodesPage.CODE_PAGE_LINKS).size();
        Map<String, String> responseCode = new HashMap<>();
        for (int i = 0; i < countLinks; i++) {
            server.newHar("the-internet");
            $(StatusCodesPage.CODE_PAGE_LINKS, i).click();
            Har har = server.getHar();
            for (HarEntry entry : har.getLog().getEntries()) {
                if (entry.getResponse().getStatus() >= 400) {
                    responseCode.put(entry.getRequest().getUrl(), String.valueOf(entry.getResponse().getStatus()));
                }
            }
            back();
        }
        Assert.assertEquals(responseCode.size(), 0, mapToString(responseCode));
    }

    private String mapToString(Map<String, String> map) {
        StringBuilder message = new StringBuilder();
        for(String key : map.keySet()) {
            message.append(String.format("\nurl: %s, response code: %s", key, map.get(key)));
        }
        message.append("\n");
        return message.toString();
    }

}
