package mobile.drivers;

import io.appium.java_client.android.options.UiAutomator2Options;
import mobile.config.Config;
import mobile.config.EmulationConfig;
import mobile.config.LocalConfig;

public class EmulationDriver extends LocalDriver {

    @Override
    protected LocalConfig config() {
        return Config.emulation;
    }

    @Override
    protected void selectDevice(UiAutomator2Options options) {
        EmulationConfig emulation = Config.emulation;
        if (emulation.deviceUdid().isBlank() && !emulation.avd().isBlank()) {
            options.setAvd(emulation.avd());
        }
    }
}
