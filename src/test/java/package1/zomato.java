package package1;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test; // Import the TestNG annotation

public class zomato {

    static WebDriver driver;

    @Test // Annotate the method with @Test
    public void forbes() throws Exception {
        // Setup ChromeDriver
    	String driverPath = System.getProperty("user.dir") + "/driver/chromedriver129.exe" ;
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to Forbes Billionaires List
        driver.get("https://www.forbes.com/billionaires/");
        Thread.sleep(20000);

        // Scroll to richest people in world header
        scrollDownToXpath("//h2[normalize-space()='The Richest People In The World']");
        Thread.sleep(5000);

        // Click on country
        driver.findElement(By.xpath("(//span[normalize-space()='country / Territory'])[1]")).click();
        // Scroll to India element from dropdown
        scrollDownToXpath("//li[@value='India']");
        // Click on India
        driver.findElement(By.xpath("//li[@value='India']")).click();
        Thread.sleep(10000);
        
        // Find and store all Indian richest people in a list
        List<WebElement> india_richest_people = driver.findElements(By.xpath("//div[contains (@class,'Table_personName')]"));

        // Output the number of richest people
        System.out.println("Number of Indian richest people in Forbes: " + india_richest_people.size());
        System.out.println();
        
        // Print the names of the richest people
        for (WebElement e : india_richest_people) {
            if (!e.getText().trim().equals("NAME")) {
                System.out.println(e.getText().trim());
            }
        }

        // Close the driver
        driver.quit(); // It's good practice to close the driver after test execution
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
}
