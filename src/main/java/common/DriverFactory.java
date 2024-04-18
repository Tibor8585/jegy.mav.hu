package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverFactory {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void webDriverSetup() {
        https:
//github.com/progmatictesztautomation23h2/Kisbaltak/blob/main/src/main/java/common/DriverFactory.java

        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(30000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofMillis(30000)); //30mp
        logMessage(": Driver letrehozasa.");
    }

    @AfterSuite
    public void webDriverTearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        logMessage(": Driver lekapcsolasa.");
    }

    private void logMessage(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}
