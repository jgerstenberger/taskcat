import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities

driver = {
	reportsDir = "build/reports/tests"
	System.setProperty("webdriver.chrome.driver","chromedriver");
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
	new ChromeDriver(capabilities)
}
