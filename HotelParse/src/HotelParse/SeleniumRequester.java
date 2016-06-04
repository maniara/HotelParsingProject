package HotelParse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumRequester {
	public static String getResponse(String url)
	{
		WebDriver driver;
		Wait<WebDriver> wait;
		driver = new FirefoxDriver ();
        wait = new WebDriverWait(driver, 30);
        driver.get(url);
        
        String source = driver.getPageSource();
        
        return source;
	}

}
