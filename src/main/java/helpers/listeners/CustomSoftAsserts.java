package helpers.listeners;

import com.codeborne.selenide.impl.SelenideLogger;
import com.codeborne.selenide.testng.SoftAsserts;

public class CustomSoftAsserts extends SoftAsserts {
    private final CustomErrorsCollector errorsCollector = new CustomErrorsCollector();

    public CustomSoftAsserts() {
        SelenideLogger.addListener(this.errorsCollector);
    }
}
