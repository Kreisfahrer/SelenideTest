import com.codeborne.selenide.SelenideElement;
import core.TestBase;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.StaticFloatingMenuPage.CONTENT_PARAGRAPHS;
import static pages.StaticFloatingMenuPage.MENU;

public class FloatingMenuTest  extends TestBase {

    @BeforeMethod
    public void setup() {
        open("floating_menu");
    }

    @Test
    public void floatingMenuTest() throws InterruptedException {
        $(MENU).shouldBe(visible);
        Point menuLocation = $(MENU).getLocation();

        for (SelenideElement paragraph : $$(CONTENT_PARAGRAPHS)) {
            paragraph.scrollTo();
            Assert.assertNotSame($(MENU).getLocation(), menuLocation);
            menuLocation = $(MENU).getLocation();
            $(MENU).shouldBe(visible);
        }
    }
}
