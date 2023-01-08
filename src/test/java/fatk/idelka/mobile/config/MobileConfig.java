package fatk.idelka.mobile.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/credentials_localandroid.properties"
})
public interface MobileConfig extends Config {
    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();

    @Key("app")
    String app();
}
