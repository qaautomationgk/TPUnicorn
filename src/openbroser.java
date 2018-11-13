import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class openbroser {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "path of the exe file\\chromedriver.exe");

		// Initialize browser
		WebDriver driver=new FirefoxDriver();

		// Open facebook
		driver.get("http://www.facebook.com");

		// Maximize browser

		driver.manage().window().maximize();

	}

}