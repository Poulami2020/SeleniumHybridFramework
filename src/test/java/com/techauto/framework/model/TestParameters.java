package com.techauto.framework.model;

/**
 * Model Class of Automation Manager. POJO class to create new Object It
 * encapsulates input parameters of script
 * 
 * @author G.R
 *
 */
public class TestParameters {
	private String testCaseId;
	private String module;
	private String testCaseName;
	private String description;
	
	
	/**
	 * Constructor to allow initialize Test Parameters object
	 * 
	 * @param testCase_ID
	 * @param moduleName
	 * @param description
	 */
	public TestParameters(String moduleName,String testCaseId) {
		this.module=moduleName;
		this.testCaseId = testCaseId;
	
		//set default as NA
		this.description="NA";

	}



	/**
	 * This function return TestCase ID
	 * 
	 * @return the testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}


	/**
	 * This function set testcase ID
	 * 
	 * @param testCaseId the testCaseId to set
	 */
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}


	public String getModule() {
		return module;
	}



	public void setModule(String module) {
		this.module = module;
	}



	public String getTestCaseName() {
		return testCaseName;
	}



	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
