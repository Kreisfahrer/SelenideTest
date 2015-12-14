package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static helpers.Helpers.getUrls;
import static helpers.Locators.get;

public class BrokenImageStaticPage {
    public final static By IMAGE_LINK = get("brokenImage.links");
    public final static By ALL_ARGUMENTS = get("brokenImage.allArguments");
    public final static String ATTRIBUTE = "src";

    public static List<String> loadImageUrls() {
        return getUrls($$(IMAGE_LINK), ATTRIBUTE);
    }

    //test for load all arguments and equals with image type
    public static List<String> loadAllArfuments() {
        return getUrls($$(ALL_ARGUMENTS), "");
    }
}
