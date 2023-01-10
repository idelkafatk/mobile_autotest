package fatk.idelka.mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import fatk.idelka.mobile.config.Mobile;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

@ParametersAreNonnullByDefault
public class MobileDriver implements WebDriverProvider {

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    @NonNull
    public WebDriver createDriver(@NonNull Capabilities capabilities) {

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName("android")
                .setDeviceName(Mobile.config.device())
                .setPlatformVersion(Mobile.config.osVersion())
                .setNewCommandTimeout(Duration.ofSeconds(11))
                .setApp(getAppPath())
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity");
        return new RemoteWebDriver(getAppiumServerUrl(), options);
    }

    private String getAppPath() {
        String appUrl = "https://github.com/saucelabs/my-demo-app-rn/releases/" +
                "download/v1.3.0/Android-MyDemoAppRN.1.3.0.build-244.apk";

        String appPath = "src/test/resources/apps/Android-MyDemoAppRN.1.3.0.build-244.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}