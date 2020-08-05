package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.techauto.framework.model.TestConfiguration;

public class AutoDriver {

	private TestConfiguration testConfiguration;
	private WebDriver driver;

	public AutoDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @return the testConfiguration
	 */
	public TestConfiguration getTestConfiguration() {
		return testConfiguration;
	}

	/**
	 * @param testConfiguration
	 *            the testConfiguration to set
	 */
	public void setTestConfiguration(TestConfiguration testConfiguration) {
		this.testConfiguration = testConfiguration;
	}

	/**
	 * Function returns WebDriver Object {@link WebDriver} object
	 * 
	 * @return the driver
	 */
	public WebDriver getWebDriver() {
		return (WebDriver) driver;
	}

	// WebDriver Methods
	/**
	 * Function to close the driver Object {@link WebDriver}
	 */
	public void close() {
		driver.close();
	}

	/**
	 * Function to wait until the specified element is visible
	 * 
	 * @param by
	 *            The locator used to identify the element {@link WebDriver}
	 */
	public boolean isElementVisible(By arg0) {
		boolean elementVisible = false;
		try {
			(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(arg0));
			elementVisible = true;

		} catch (TimeoutException ex) {
			elementVisible = false;
		}
		return elementVisible;
	}

}
