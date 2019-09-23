package main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class TakeCapture  {
    public static void main(String[] args) {
    	try {
    		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
    	    final ChromeOptions chromeOptions = new ChromeOptions();
    	    chromeOptions.addArguments("--no-sandbox");
    	    chromeOptions.addArguments("--headless");
    	    final Map<String, String> environmentVariables = new HashMap<>();
    	    final ChromeDriverService service = new ChromeDriverService.Builder()
    	            .usingAnyFreePort()
    	            .withEnvironment(environmentVariables)
    	            .build();
    	    final WebDriver driver = new ChromeDriver(service, chromeOptions);
	        driver.get(args[0]);
	        try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }
			Dimension d = driver.findElement(By.cssSelector(args[1])).getSize();
			/*int sideBarWith = getSideBarWidth(driver, args[3]);
			driver.manage().window().setSize(new Dimension(d.width + sideBarWith, d.height));*/
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(args[2]));
			driver.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static int getSideBarWidth(WebDriver driver, String cssSelector){
    	int width = 0;
    	try {
    		width = driver.findElement(By.cssSelector(cssSelector)).getSize().getWidth();
    	} catch (org.openqa.selenium.NoSuchElementException e) {
    		width = 0;
        }
    	return width;
    }
}
