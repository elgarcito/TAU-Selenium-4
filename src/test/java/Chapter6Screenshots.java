import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Chapter6Screenshots {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://applitools.com/");
    }

    @Test
    public void takeWebElementScreenshot(){
        WebElement nextGeneerationPlatform=driver.findElement(
                By.cssSelector("#post-8 h1"));
        File source= nextGeneerationPlatform.getScreenshotAs(OutputType.FILE);
        File destination=new File("Next generation platform.png");
        try {
            FileHandler.copy(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void takeWebElementPageSectionScreenshot(){
        WebElement applitoolsPageSection=driver.findElement(
                By.cssSelector("#post-8>header"));
        File source= applitoolsPageSection.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source,new File("Next generation platform with image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void takeFullPageScreenshot(){
        File source= ((FirefoxDriver)driver).getFullPageScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(source,new File("Full screnshoot firefox.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
