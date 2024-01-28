import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Chapter3RelativesLocators {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testRelativeLocator(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("app")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h5")));
        WebElement loginPanel=driver.findElement(By.tagName("h5"));
        //System.out.println(loginPanel.getText());

        WebElement username=driver.findElement(RelativeLocator.with(
                By.tagName("p")
        ).below(loginPanel));
        System.out.println(username.getText());
        WebElement password=driver.findElement(RelativeLocator.with(
                By.tagName("p")
        ).below(username));
        System.out.println(password.getText());
    }

    @Test
    public void testListOfElements(){

        //This doesn't work because is no longer below or near the last element but is the idea
        /*
        List<WebElement> allSocialMedia=driver.findElements(
                with(By.tagName("a"))
                        .near(By.className("orangehrm-login-footer-sm")));

         */
        List<WebElement> allSocialMedia=driver.findElements(
                By.cssSelector("div>a"));
        allSocialMedia.forEach(x-> System.out.println(String.format("The link is: %s",x.getAttribute("href"))));


    }

}
