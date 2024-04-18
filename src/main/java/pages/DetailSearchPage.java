package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetailSearchPage {
    private final WebDriver driver;
    private final By searchButtonBy = By.xpath("//div[@class='title']//button");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DetailSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchButtonClick() {
        WebElement searchButton = driver.findElement(searchButtonBy);
        searchButton.click();
        logMessage(": Kattintas a kereses gombra.");
    }
    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}