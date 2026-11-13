package mobile.drivers;

import io.appium.java_client.android.options.UiAutomator2Options;
import mobile.config.Config;
import mobile.config.LocalConfig;

public class RealDeviceDriver extends LocalDriver {

    @Override
    protected LocalConfig config() {
        return Config.realDevice;
    }

    @Override
    protected void selectDevice(UiAutomator2Options options) {
        if (Config.realDevice.deviceUdid().isBlank()) {
            throw new IllegalArgumentException(
                    "-Ddevice.udid=<серийный номер из adb devices>");
        }
    }
}
