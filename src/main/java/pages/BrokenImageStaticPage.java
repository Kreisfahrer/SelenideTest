package pages;

import org.openqa.selenium.By;
import static helpers.Locators.get;

public class BrokenImageStaticPage {
    public final static By IMAGE_LINK = get("brokenImage.links");
    public final static String ATTRIBUTE = "src";
}
