package RequestHandler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

}
