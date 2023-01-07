package fatk.idelka.mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import fatk.idelka.mobile.config.Mobile;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    @NonNull
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", Mobile.config.browserstackUser());
        mutableCapabilities.setCapability("browserstack.key", Mobile.config.browserstackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", Mobile.config.app());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", Mobile.config.device());
        mutableCapabilities.setCapability("os_version", Mobile.config.osVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }
}