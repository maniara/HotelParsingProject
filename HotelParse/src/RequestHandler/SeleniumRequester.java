package RequestHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumRequester {
	public static WebDriver driver;
	
	public static String getResponse(String url)
	{
		if(driver == null)
			driver = new FirefoxDriver ();
		Wait<WebDriver> wait;
        wait = new WebDriverWait(driver, 30);
        driver.get(url);
        
        String source = driver.getPageSource();
        
        //driver.close();
        return source;
	}
	
	//Agora needs to change currency to KRW
	public static String getAgodaResponse(String url)
	{
		if(driver == null)
			driver = new FirefoxDriver ();
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        driver.get(url);
        driver.findElement(By.cssSelector(("a[data-selenium='currency']"))).click();
        driver.findElement(By.cssSelector(("a[data-selenium='currency-code'][title='ÇÑ±¹ ¿ø']"))).click();

        //wait until currency changed
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String source = driver.getPageSource();
        
        return source;
	}

}
