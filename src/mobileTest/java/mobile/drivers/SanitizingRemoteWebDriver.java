package mobile.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SanitizingRemoteWebDriver extends RemoteWebDriver {

    public SanitizingRemoteWebDriver(URL remoteAddress, Capabilities capabilities) {
        super(remoteAddress, capabilities);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        String base64 = (String) execute(DriverCommand.SCREENSHOT).getValue();
        return target.convertFromBase64Png(base64.replaceAll("\\s", ""));
    }
}
