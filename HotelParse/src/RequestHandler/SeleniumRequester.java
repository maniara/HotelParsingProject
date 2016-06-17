package RequestHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.htmlparser.jericho.Source;

//There needs to wait time for loading page complete.
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
        
        String source = driver.getPageSource();
        Source s = new Source(source);
        
        while(s.getFirstElementByClass("currency anchor") == null){
        	System.out.println("LOG : Currency element is null");
            	try {
    				Thread.sleep(1000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
			s = new Source(driver.getPageSource());
			//System.out.println("LOG : " + s.getFirstElementByClass("currency anchor"));
        }
        	
		if (!s.getFirstElementByClass("currency anchor").getContent().toString().equals("KRW"))
		{
			System.out.println("LOG : Currency changing");
	        driver.findElement(By.cssSelector(("a[data-selenium='currency']"))).click();
	        driver.findElement(By.cssSelector(("li[data-id='26']"))).click();
	        
	        //not to work WebDriverWait
	        try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        source = driver.getPageSource();
		}
        
        return source;
	}

}
