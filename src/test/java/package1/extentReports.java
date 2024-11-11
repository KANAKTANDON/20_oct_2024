package package1;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class extentReports {

	
	static WebDriver driver;
	String reportPath = System.getProperty("user.dir") + "/extentReports" + "/reports.html";
	ExtentReports reports = new ExtentReports(reportPath);
	static ExtentTest test;

    @Test // Annotate the method with @Test
    public void test1() throws Exception {
        test = reports.startTest("Donald Trump Google Search");

        // Setup ChromeDriver
        String driverPath = System.getProperty("user.dir") + "/driver/chromedriver129.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            //open google page
            driver.get("https://www.google.com/");
        	
          
            Thread.sleep(5000);
            
            test.log(LogStatus.PASS, "Google Url Opened", test.addScreenCapture(takeScreenshot("googleUrl")));
            Thread.sleep(2000);

            // search donald trump
            driver.findElement(By.id("APjFqb")).sendKeys("Donald Trump"+Keys.ENTER);
            Thread.sleep(5000);
            test.log(LogStatus.PASS, "donald trump search results",
            test.addScreenCapture(takeScreenshot("donaldtrump_searchresults")));
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 600)");
            Thread.sleep(2000);
            test.log(LogStatus.PASS, "donald trump search results",
                    test.addScreenCapture(takeScreenshot("donaldtrump_searchresults2")));
            
            
            driver.navigate().to("https://trumpwhitehouse.archives.gov/people/donald-j-trump/");
            Thread.sleep(3000);
            test.log(LogStatus.PASS, "trump white house official website ",
                    test.addScreenCapture(takeScreenshot("trump white house")));
            
            System.out.println("Test Passed, check successfull");
           
        } catch (Exception e) {
        	System.out.println(e);
            test.log(LogStatus.FAIL, "some exception occurred: " + e);
        } finally {
            Thread.sleep(5000);
            System.out.println("teardown begins ...");
            driver.quit();
            reports.endTest(test);
            reports.flush();
            reports.close();
        }
    }


    public static void scrollDownToXpath(String xpath) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = driver.findElement(By.xpath(xpath));

        int counter = 1;
        while (counter < 5) {
            // If the element is found, exit the loop
            if (e.isDisplayed()) {
                break;
            } else {
                System.out.println("Element not found");
                js.executeScript("window.scrollBy(0,50)");
                Thread.sleep(5000);
                counter++;
            }
        }
    }
    
    public static String takeScreenshot(String name) throws Exception {
		String screenshotDestination = System.getProperty("user.dir") + "/screenshots/"+name+".jpg";
		TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
		File sourceFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotDestination);
		Files.copy(sourceFile, dest);
		return dest.getAbsolutePath();
	}

}
