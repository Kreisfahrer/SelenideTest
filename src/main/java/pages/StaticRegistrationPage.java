package pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class StaticRegistrationPage {
    public final static By USER_NAME_FIELD = get("registerPage.userNameField");
    public final static By PASSWORD_FIELD = get("registerPage.passwordField");
    public final static By LOGIN_BUTTON = get("registerPage.loginButton");
    public final static By FLASH = get("registerPage.flash");
    public final static By FLASH_ERROR = get("registerPage.flashError");

    @Step("Logging in with user: {0}, password: {1}.")
    public static void login(String user, String pass) {
        $(USER_NAME_FIELD).val(user);
        $(PASSWORD_FIELD).val(pass);
        $(LOGIN_BUTTON).click();
    }
}
