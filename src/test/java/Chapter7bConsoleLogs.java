import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Chapter7bConsoleLogs {
    //ChromeDriver driver;
    EdgeDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        //driver=new ChromeDriver();
        driver=new EdgeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void viewBrowserConsoleLogs(){
        //Get the DevTools & Create a session
        DevTools devTools= driver.getDevTools();
        devTools.createSession();
        //Enable the console logs
        devTools.send(Log.enable());
        //Add listener for the console logs
        devTools.addListener(Log.entryAdded(),logEntry -> {
            System.out.println("----------");
            System.out.println("Level: "+ logEntry.getLevel());
            System.out.println("Text: "+ logEntry.getText());
            System.out.println("Broken Url: "+ logEntry.getUrl());
        });
        //Load the AUT
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }
}
