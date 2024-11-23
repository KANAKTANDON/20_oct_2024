package package1;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class baseclass {
    public static WebDriver driver;
    static ExtentReports reports;   // Change to static for shared report instance
    ExtentTest test;
    
    // Initialize ExtentReports only once in @BeforeClass
    @BeforeClass
    public void setupReports() throws Exception {
        String reportPath = System.getProperty("user.dir") + "/extentReports/reports.html";
        reports = new ExtentReports(reportPath);
        System.out.println("Reports initialized at: " + reportPath);
    }

    // Initialize WebDriver and start a new test in @BeforeMethod
    @BeforeMethod
    public void setup(ITestResult result) throws Exception {
        System.out.println("Executing test case: " + result.getMethod().getMethodName());
        
        // Initialize WebDriver
        String driverPath = System.getProperty("user.dir") + "/driver/chromedriver129.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(1000);
        
        // Start a new test in ExtentReports
        test = reports.startTest(result.getMethod().getMethodName());
        System.out.println("-----------------------------------------------");
    }
    
    // Close WebDriver and end the test in @AfterMethod
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        
        // Take screenshot and log it
        test.addScreenCapture(takeScreenshot(result.getMethod().getMethodName()));
        
        driver.quit();
        reports.endTest(test);  // End the test
        reports.flush();  // Flush the report to save the results
    }

    // Initialize driver and take a screenshot
    public static String takeScreenshot(String name) throws Exception {
        String screenshotDestination = System.getProperty("user.dir") + "/screenshots/"+name+".jpg";
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
        File sourceFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotDestination);
        Files.copy(sourceFile, dest);
        return dest.getAbsolutePath();
    }

    // Close the reports in @AfterClass
    @AfterClass
    public void closeReports() {
        reports.close();  // Close the reports when all tests are done
    }
}
