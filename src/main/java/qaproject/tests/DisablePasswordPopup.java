package qaproject.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import qaproject.utils.DriverManager;

public class DisablePasswordPopup {
	
	@Test(dependsOnMethods = "cartcheckoutTest", description = "DisablePasswordPopup") 
	public void disablepopup() {

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);
		DriverManager.driver = driver;
		driver.quit();

	}

}
