package com.techauto.framework.model;
import com.techauto.framework.Browser;
import com.techauto.framework.ExecutionMode;


public class TestConfiguration extends TestParameters {

	private ExecutionMode executionMode;
	private Browser browser;
	private String browserVersion;
	//private ToolName toolName;

	public TestConfiguration( String moduleName,String testCaseId) {
		super(moduleName,testCaseId);
	
	}

	/**
	 * @return the executionMode
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * @param executionMode the executionMode to set
	 */
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	/**
	 * @return the browser
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * @return the browserVersion
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * @param browserVersion the browserVersion to set
	 */
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	
	
}
