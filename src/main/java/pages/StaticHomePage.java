package pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class StaticHomePage {
    public final static By FLASH = get("homePage.flash");
    public final static By LOGOUT_BUTTON = get("homePage.logoutButton");

    @Step("Logging out")
    public static void logout() {
        $(LOGOUT_BUTTON).click();
    }
}
