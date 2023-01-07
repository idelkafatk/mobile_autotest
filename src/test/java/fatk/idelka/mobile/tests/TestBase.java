package fatk.idelka.mobile.tests;

import com.codeborne.selenide.Configuration;
import fatk.idelka.mobile.drivers.BrowserstackMobileDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static fatk.idelka.mobile.helpers.Attachments.*;

public class TestBase {
    @BeforeAll
    public static void setup() {
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.browserSize = null;
        addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void startDriver() {
        open();
    }

    @AfterEach
    public void tearDown() {
        String sessionId = sessionId().toString();

        screenshotAs("Last screenshot");
        pageSource();

        closeWebDriver();

        addVideo(sessionId);
    }
}
