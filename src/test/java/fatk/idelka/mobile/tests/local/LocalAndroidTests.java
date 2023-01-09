package fatk.idelka.mobile.tests.local;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Тестирование на андроид")
public class LocalAndroidTests extends LocalTestBase {
    @Test
    void checkOpenedArticleIsCorrect(){
        step("Types search article", () -> {
//            Configuration.timeout = 60000;
            $(AppiumBy.accessibilityId("cart badge")).click();
            $(AppiumBy.accessibilityId("Go Shopping button")).shouldBe(visible);
        });

    }

}
