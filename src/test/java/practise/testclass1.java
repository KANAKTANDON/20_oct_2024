package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testclass1 extends baseclass{

	
	
	@Test
	public void testcase1() {
		System.out.println("This is testcase1 execution");
		driver.get("https://en.wikipedia.org/wiki/OpenAI");
		test.log(LogStatus.PASS, "open ai website");
		
	}
	
	@Test
	public void testcase2() {
		System.out.println("This is testcase2 execution");
		driver.get("https://ai.google/");
		test.log(LogStatus.PASS, "google ai website");
		
	}
	
}
