package launcher;

import java.io.File;
import java.util.List;
import java.util.Properties;

import com.techauto.framework.FrameworkSettings;
import com.techauto.framework.GlobalParameters;
import com.techauto.framework.model.TestConfiguration;
import com.techauto.framework.services.AutomationMgrServices;

import driver.DriverScripts;

public class AutoPilot {
	
	private Properties properties=FrameworkSettings.getInstance();
	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	private AutomationMgrServices automationManagerServices=new AutomationMgrServices();
	
	
	
	public static void main(String[] args) {
		System.out.println("Start");
		AutoPilot launch = new AutoPilot();
		launch.startBatchExecution();
	}

	private void startBatchExecution() {
		setRelativePath();

		String suiteName;
		if (System.getProperty("RunWorksheet") != null) {
			suiteName = System.getProperty("RunWorksheet");
		} else {
			suiteName = properties.getProperty("RunWorksheet");
		}
		globalParameters.setRunSuite(suiteName);

		execute();
	}

	private void execute() {
		List<TestConfiguration> testInstancesToRun = automationManagerServices.getRunInfo(globalParameters,properties);

		for (int currentTestInstance = 0; currentTestInstance < testInstancesToRun.size(); currentTestInstance++) {
//			System.out.println(testInstancesToRun.get(currentTestInstance).getDescription());
//			System.out.println(testInstancesToRun.get(currentTestInstance).getModule());
//			System.out.println(testInstancesToRun.get(currentTestInstance).getTestCaseId());
//			System.out.println(testInstancesToRun.get(currentTestInstance).getExecutionMode());
//			System.out.println(testInstancesToRun.get(currentTestInstance).getBrowser());
//			System.out.println("**********************************************");
			DriverScripts driverScript = new DriverScripts(testInstancesToRun.get(currentTestInstance));
			driverScript.driveTestExecution();
			System.out.println("Done");
		
		}
		
		
	}

	/**
	 * 
	 * Convinient Methods
	 * 
	 */
	private void setRelativePath() {
		String encryptedPath = System.getProperty("user.dir");
		String relativePath = new File(encryptedPath).getAbsolutePath();
		if (relativePath.contains("supportlibraries")) {
			relativePath = new File(encryptedPath).getParent();
		}
		globalParameters.setRelativePath(relativePath);
	}
}
