import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Iterator;
import java.util.Set;

public class Chapter4WindowManagement {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().minimize();
        driver.get("https://the-internet.herokuapp.com/");
        System.out.println(String.format("Title: %s", driver.getTitle()));
    }

    @Test
    public void testNewWindowTab(){
       WebDriver newWindowDriver= driver.switchTo().newWindow(WindowType.TAB);
       driver.manage().window().minimize();
       newWindowDriver.get("https://the-internet.herokuapp.com/login");
       System.out.printf("Title: %s",driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowsTabs(){
        //Automatically open & switch to the new window or tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("https://the-internet.herokuapp.com/login");
        driver.manage().window().minimize();
        System.out.printf("Title: %s",driver.getTitle());
        //Work in the new window or tab
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.tagName("button")).click();
        //Get window id handels
        Set<String> allWindowsTabs=driver.getWindowHandles();
        Iterator<String> iterate= allWindowsTabs.stream().iterator();
        String mainFirstWindow= iterate.next();
        //Switch & work in the main window or tab
        driver.switchTo()
                .window(mainFirstWindow)
                .findElement(By.linkText("Context Menu"))
                .click();

        driver.manage().window().minimize();
        System.out.printf("Title: %s",driver.getTitle());
    }
}
