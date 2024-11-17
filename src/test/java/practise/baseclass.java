package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class baseclass {
	public WebDriver driver;
	 public ExtentReports report;
	public ExtentTest test;

	@BeforeClass
	public void reportsetup() {
		 //extent reports setup
        String reportPath = System.getProperty("user.dir") + "/extentReports/reports.html";
        report = new ExtentReports(reportPath);
	}
	
	@BeforeMethod
	public void setup(ITestResult result) throws Exception {
		
		//driver setup
		String driverPath = System.getProperty("user.dir") + "/driver/chromedriver129.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(1000);
        
       
        test = report.startTest(result.getMethod().getMethodName());
        
	}
	
	@AfterMethod
	public void teardown() throws Exception{
		report.endTest(test);
		report.flush();
		
		 Thread.sleep(2000);
		driver.quit();
	}
	
	@AfterClass
	public void afterclass() {
		report.close();
	}
	
}
