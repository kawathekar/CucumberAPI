package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.codehaus.plexus.util.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//@Listeners(EmailTestResult.class)
@CucumberOptions(
				features = ("D:\\SeleniumBaseProjectRepository\\AQM.APITest.com\\src\\Features"),
				glue = {"Main"},
				plugin = { "pretty", "html:target/cucumber-reports/report",
				"json:target/cucumber-reports/report.json",
				"com.cucumber.listener.ExtentCucumberFormatter:target/"
						+ "ExtentReport/ExtentreportHTML.html" }, 
				monochrome = true,
				dryRun = false, tags = "@Test")

public class TestRunner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {

		File file = new File(
				"D:\\SeleniumBaseProjectRepository\\AQM.APITest.com\\" + "src\\test\\java\\Main\\DataFile.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Reporter.loadXMLConfig(
				"D:\\SeleniumBaseProjectRepository\\AQM.APITest.com\\src\\test\\java\\Main\\extent-config1.xml");
		Reporter.assignAuthor("Abhishek");
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10, " + "64 Bit");
		Reporter.setSystemInfo("Selenium", "3.11.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_121");
		testNGCucumberRunner.finish();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		Date date = new Date();
		String returnDate = formatter.format(date);
		File dest = new File(
				System.getProperty("user.dir") + "\\Execution_Result\\ExecutionResultsSummarycucumber\\" + returnDate);
		dest.mkdirs();
		File source = new File(System.getProperty("user.dir") + "\\target\\ExtentReport");
		File source2 = new File(System.getProperty("user.dir") + "\\target\\cucumber-reports");
		try {
			FileUtils.copyDirectory(source, dest);
			FileUtils.copyDirectory(source2, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	@AfterSuite(alwaysRun = true)
	public void mailcall() {
		 Emailtest a = new  Emailtest();
		a.onFinish(null);	
	
	}
		}
