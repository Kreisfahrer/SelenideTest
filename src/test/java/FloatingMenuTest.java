import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.TestBase;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import static pages.StaticFloatingMenuPage.MENU;
import static pages.StaticFloatingMenuPage.CONTENT_PARAGRAPHS;

public class FloatingMenuTest  extends TestBase{

    @BeforeMethod
    public void setup() {
        open("floating_menu");
    }

    @Test
    public void floatingMenuTest() throws InterruptedException {
        $(MENU).shouldBe(visible);
        Point menuLocation = $(MENU).getLocation();

        ElementsCollection paragraphs = $$(CONTENT_PARAGRAPHS);
        for (SelenideElement paragraph : paragraphs) {
            paragraph.scrollTo();
            Assert.assertNotSame($(MENU).getLocation(), menuLocation);
            menuLocation = $(MENU).getLocation();
            $(MENU).shouldBe(visible);
        }
    }

}
