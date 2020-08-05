package com.techauto.framework.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.techauto.framework.Browser;
import com.techauto.framework.ExecutionMode;
import com.techauto.framework.GlobalParameters;
import com.techauto.framework.Utility;
import com.techauto.framework.dao.ExcelAccess;
import com.techauto.framework.model.TestConfiguration;

public class AutomationMgrServices {
	
	
	
	public List<TestConfiguration> getRunInfo(GlobalParameters globalParameters,Properties properties) {
		String sheetName=globalParameters.getRunSuite();
		ExcelAccess runManagerAccess = new ExcelAccess(globalParameters.getRelativePath() + Utility.getFileSeperator()
				+ "src" + Utility.getFileSeperator() + "test" + Utility.getFileSeperator() + "resources",
				"AutomationManager");
		runManagerAccess.setDataSheetName(sheetName);

		List<TestConfiguration> testInstancesToRun = new ArrayList<TestConfiguration>();
		String[] keys = { "Run", "Module", "TestCaseId", "TestCaseName", "Description", "ConfigurationId" };
		List<Map<String, String>> values = runManagerAccess.getValues(keys);

		for (int currentTestInstance = 0; currentTestInstance < values.size(); currentTestInstance++) {

			Map<String, String> row = values.get(currentTestInstance);
			String executeFlag = row.get("Run");

			if (executeFlag.equalsIgnoreCase("Y")) {
				String currentModuleName = row.get("Module");
				String currentTestcaseId = row.get("TestCaseId");
				TestConfiguration testParameters = new TestConfiguration(currentModuleName, currentTestcaseId);
				testParameters.setTestCaseName(row.get("TestCaseName"));
				testParameters.setDescription(row.get("Description"));

				String testConfig = row.get("ConfigurationId");
				if (!"".equals(testConfig)) {
					getTestConfigValues(runManagerAccess, "TestConfiguration", testConfig, testParameters,properties);
				}

				testInstancesToRun.add(testParameters);
				runManagerAccess.setDataSheetName(sheetName);
			}
		}
		return testInstancesToRun;
	}

	private void getTestConfigValues(ExcelAccess runManagerAccess, String sheetName, String testConfigName,
			TestConfiguration testParameters,Properties properties) {

		runManagerAccess.setDataSheetName(sheetName);
		int rowNum = runManagerAccess.getRowNum(testConfigName, 0, 1);

		String[] keys = { "ConfigurationId", "ExecutionMode", "ToolName", "Browser", "BrowserVersion" };
		Map<String, String> values = runManagerAccess.getValuesForSpecificRow(keys, rowNum);

		String executionMode = values.get("ExecutionMode");
		if (!"".equals(executionMode)) {
			testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
		} else {
			// testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
		}

		// String toolName = values.get("ToolName");
		// if (!"".equals(toolName)) {
		// testParameters.setMobileToolName(ToolName.valueOf(toolName));
		// } else {
		// testParameters.setMobileToolName(ToolName.valueOf(mobileProperties.getProperty("DefaultMobileToolName")));
		// }

		String browser = values.get("Browser");
		if (!"".equals(browser)) {
			testParameters.setBrowser(Browser.valueOf(browser));
		} else {
			testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
		}

		String browserVersion = values.get("BrowserVersion");
		if (!"".equals(browserVersion)) {
			testParameters.setBrowserVersion(browserVersion);
		}

	}
}
