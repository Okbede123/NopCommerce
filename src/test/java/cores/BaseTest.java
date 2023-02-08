package cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class BaseTest {
    WebDriver driver;

    public WebDriver openBrowser(String browser) throws Exception {
        switch (browser){
            case "chrome":{
                driver = new ChromeDriver();
                break;
            }
            case "firefox":{
                driver = new FirefoxDriver();
                break;
            }
            case "edge":{
                driver = new EdgeDriver();
                break;
            }
            default:{
                throw new Exception("not found driver");
            }
        }
        return driver;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public int getRandomNum(){
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    public void sleepInTime(int time){
        try {
            Thread.sleep(time* 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
