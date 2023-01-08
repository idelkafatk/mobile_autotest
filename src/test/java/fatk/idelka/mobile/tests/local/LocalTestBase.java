package fatk.idelka.mobile.tests.local;

import com.codeborne.selenide.Configuration;
import fatk.idelka.mobile.drivers.MobileDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static fatk.idelka.mobile.helpers.Attachments.*;

public class LocalTestBase {
    @BeforeAll
    public static void setup() {
        Configuration.browser = MobileDriver.class.getName();
        Configuration.browserSize = null;
        addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void startDriver() {
        open();
    }

    @AfterEach
    public void tearDown() {
        screenshotAs("Last screenshot");
        pageSource();

        closeWebDriver();
    }
}
