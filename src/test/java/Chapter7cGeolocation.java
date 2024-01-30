import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Chapter7cGeolocation {
    ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void mockGeoLocation() {
        Map coordinates = new HashMap() {{
            put("latitude", 32.746940);
            put("longitude", -97.092400);
            put("accuracy", 1);
        }};
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("https://where-am-i.org/");
    }

    @Test
    public void mockGeoLocationWithDevTools() {
        DevTools devTools=driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(32.746940),
                Optional.of(-97.092400),
                Optional.of(1)));
        driver.get("https://where-am-i.org/");
    }
}