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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class pmo extends baseclass {

	@Test( priority = 1)
	public void TC_list_of_indian_prime_ministers() throws Exception {

		driver.get("https://www.pmindia.gov.in/en/");
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "pmo website open", test.addScreenCapture(takeScreenshot("pmo website")));

		// click menu
		driver.findElement(By.xpath("//i[@class='icon-align-justify']")).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "menu icon clicked", test.addScreenCapture(takeScreenshot("menu icon clicked")));

		// click former prime ministers
		driver.findElement(By.xpath("//a[contains (text(),'Former Prime Ministers')]")).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "former prime minister fetched",
				test.addScreenCapture(takeScreenshot("former prime minister fetched0")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		//scroll down
		for (int i=1; i<=6; i++) {
			js.executeScript("window.scrollBy(0, 700)");
			test.log(LogStatus.PASS, "former prime ministers list"+i,
					test.addScreenCapture(takeScreenshot("former prime minister fetched"+i)));
			Thread.sleep(2000);
			}
		
		

		

		String xpath = "//div[@class='former-description']/h3 | //div[@class='former-description']/span";
		List<WebElement> list = driver.findElements(By.xpath(xpath));

		int counter = 0;
		for (WebElement i : list) {
			System.out.println(i.getText());
			//test.log(LogStatus.INFO, i.getText());
			counter++;

			if (counter == 2) {
				counter = 0;
				System.out.println("\n");
			}
		}

	}
	
	@Test ( priority = 2)
    public void TC_donald_trump_google_search() throws Exception {

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
        } 
    }

}
