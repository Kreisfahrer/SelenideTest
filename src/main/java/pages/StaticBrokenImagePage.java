package pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static helpers.Helpers.getUrls;
import static helpers.Locators.get;
import static helpers.Locators.getLocatorString;

public class StaticBrokenImagePage {
    private final static By URLS_IMG = get("brokenImage.urlsImg");
    private String attributeImg = "src";
    private String pathLinkUrl = "broken_images";

    @Step ("here built a list links")
    public Map<String, String> imagesLoadedTest() {
        List<String> urls = getUrls($$(URLS_IMG), attributeImg);
        Map<String, String> brokenImages = new HashMap<>();
        for (String link : urls) {

        }
        return brokenImages;
    }

    public String getPathLinkUrl() {
        return pathLinkUrl;
    }
}
