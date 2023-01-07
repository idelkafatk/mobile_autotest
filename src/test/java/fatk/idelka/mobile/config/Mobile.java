package fatk.idelka.mobile.config;

import org.aeonbits.owner.ConfigFactory;

public class Mobile {
    public static MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
}
