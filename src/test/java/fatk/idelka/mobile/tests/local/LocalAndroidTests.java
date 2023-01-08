package fatk.idelka.mobile.tests.local;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Тестирование на андроид")
public class LocalAndroidTests extends LocalTestBase {
    @Test
    void checkOpenedArticleIsCorrect(){
        back();
        step("Types search article", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Coca-cola");
        });
        step("Check content", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0)));
        step("Choose page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
            back();
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
        });
        step("Verify opened page", () ->
                $(AppiumBy.className("android.widget.TextView")).shouldHave(text("Coca-cola")));
    }

}
