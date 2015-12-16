package pages;


import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class AuthenticatedPage {
    public final static By FLASH_SUCCESS = get("loginPage.flashSuccess");
    public final static By LOGOUT_BUTTON = get("loginPage.logoutButton");

    public static void logOut(){
        $(LOGOUT_BUTTON).click();
    }

}
