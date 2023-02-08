package cores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public String castToParameter(String locator,String...values){
        return String.format(locator,(Object[])values);
    }

    public By getBylocator(String locator,String...values){
        By locatorCut;
        String castParameter = castToParameter(locator,values);
        if(castParameter.startsWith("ID=") || castParameter.startsWith("Id=") || castParameter.startsWith("id=")){
            locatorCut = By.id(castParameter.substring(3));
        }
        else if (castParameter.startsWith("xpath=") || castParameter.startsWith("Xpath=") || castParameter.startsWith("XPATH=")) {
            locatorCut =  By.xpath(castParameter.substring(6));
        }
        else if (castParameter.startsWith("Css=")|| castParameter.startsWith("CSS=") || castParameter.startsWith("css=")) {
            locatorCut = By.cssSelector(castParameter.substring(4));
        }
        else if(castParameter.startsWith("Name=")|| castParameter.startsWith("NAME=") || castParameter.startsWith("name=")){
            locatorCut = By.cssSelector(castParameter.substring(5));
        }
        else if(castParameter.startsWith("Class=")|| castParameter.startsWith("class=") || castParameter.startsWith("CLASS=")){
            locatorCut = By.className(castParameter.substring(6));
        }
        else {
            throw new RuntimeException("Locator not valid");
        }
        return locatorCut;
    }

    public WebElement searchElement(String locator,String...values){
       return driver.findElement(getBylocator(locator,values));
    }

    public void clickToElement(String locator,String... values){
        searchElement(locator,values).click();
    }

    public void sendKeyToElement(String locator,String valueToSend,String...values){
        searchElement(locator,values).sendKeys(valueToSend);
    }

    public String getText(String locator,String... values){
        return searchElement(locator,values).getText();
    }

    public void waitElementVisibility(String locator,String...values){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getBylocator(locator,values)));
    }

    public void waitElementInvisibility(String locator,String...values){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getBylocator(locator,values)));
    }

    public void waitElementClick(String locator,String...values){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getBylocator(locator,values)));
    }

    public List<WebElement> listElements(String locator,String...values){
        return driver.findElements(getBylocator(locator,values));
    }

    public String getWindowHandle(){
       return driver.getWindowHandle();
    }

    public void switchToWindow(String id){
        driver.switchTo().window(id);
    }

    public Set<String> getListWindowHandles(){
        return driver.getWindowHandles();
    }

    public String getTitle(){
       return driver.getTitle();
    }

    public void switchToWindowByTitle(String titleExpected){
        if(getListWindowHandles().size()> 1){
            for (String winId:getListWindowHandles()) {
                switchToWindow(winId);
                if(getTitle().equals(titleExpected)){
                    break;
                }
            }
        }
    }

    public void switchToWindowById(){}
}
