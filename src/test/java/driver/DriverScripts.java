package driver;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.techauto.framework.Browser;
import com.techauto.framework.ExecutionMode;
import com.techauto.framework.FrameworkException;
import com.techauto.framework.FrameworkSettings;
import com.techauto.framework.model.TestConfiguration;

public class DriverScripts {

	private final TestConfiguration testConfiguration;
	private Properties properties;
	private AutoDriver driver;
	private WebDriver wd;

	/**
	 * Driver Scripts constructor
	 * 
	 * @param {@link
	 * 			TestConfiguration} object
	 */
	public DriverScripts(TestConfiguration testConfiguration) {
		this.testConfiguration = testConfiguration;
	}

	/**
	 * Function to execute the test case
	 */
	public void driveTestExecution() {
		startUp();

		initializeWebDriver();
		// initializeTestReport();
		// initializeDatatable();
		// executeCraftOrCraftLite();
		quitWebDriver();
		// wrapUp();
	}

	private void startUp() {
		properties = FrameworkSettings.getInstance();
		defaultSetup();
	}

	private void defaultSetup() {
		if (testConfiguration.getExecutionMode() == null) {
			testConfiguration.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
		}
		if (testConfiguration.getBrowser() == null) {
			testConfiguration.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
		}
	}

	private void initializeWebDriver() {

		switch (testConfiguration.getExecutionMode()) {

		case LOCAL:
			WebDriver webDriver = WebDriverFactory.getWebDriver(testConfiguration.getBrowser());
			driver = new AutoDriver(webDriver);
			wd = webDriver;
			driver.setTestConfiguration(testConfiguration);

			maximizeWindow();
			
			//This piece of code to wait for testing purpose.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			break;
		default:
			throw new FrameworkException("Unhandled Execution Mode!");

		}

	}

	private void quitWebDriver() {
		switch (testConfiguration.getExecutionMode()) {

		case LOCAL:
			wd.quit();
			break;
		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}

	}

	private void maximizeWindow() {
		wd.manage().window().maximize();
	}
}
