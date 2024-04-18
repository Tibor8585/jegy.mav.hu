package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TrainPage {
    private final WebDriver driver;
    private final By timeAndRoadMenuButtonBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-purchase/div/div/aside/app-status-line/app-desktop-status-line/app-desktop-status-line-step-frame[1]/div[2]/a");
    private final By firstAnswerBy = By.cssSelector("button[class='d-flex ticket-button blue']");
    private final By gePriceBy = By.cssSelector("div[class='price text-right']");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TrainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void timeAndRoadMenuButtonClick() throws InterruptedException {
        WebElement timeAndRoadMenuButton = driver.findElement(timeAndRoadMenuButtonBy);
        Thread.sleep(1000);
        timeAndRoadMenuButton.click();
        logMessage(": Idopont menure kattintas.");
    }

    public void firstAnswer() throws InterruptedException {
        WebElement firstAnswer = driver.findElement(firstAnswerBy);
        Thread.sleep(1000);
        firstAnswer.click();
        logMessage(": Elso lehetoseg kivalasztasa.");
    }

    public String getLowestPrice() {
        List<WebElement> prices = driver.findElements(gePriceBy);
        String lowestPriceString = prices.get(0).getText();
        logMessage(": Kedvezmenyes ar ellenorzese.");
        return lowestPriceString;
    }

    public String getHighestPrice() {
        List<WebElement> prices = driver.findElements(gePriceBy);
        String highestPriceString = prices.get(1).getText();
        logMessage(": Magasabb ar ellenorzese.");
        return highestPriceString;
    }

    public String getFullPrice() {
        List<WebElement> prices = driver.findElements(gePriceBy);
        String fullPriceString = prices.get(0).getText();
        logMessage(": A teljes ar ellenorzese.");
        return fullPriceString;
    }

    public String getFreePrice() {
        List<WebElement> prices = driver.findElements(gePriceBy);
        String fullPriceString = prices.get(0).getText();
        logMessage(": Teljes kedvezmeny ellenorzese.");
        return fullPriceString;
    }

    public void chooseFirstAnswer() {
        WebElement firstAnswer = driver.findElement(firstAnswerBy);
        firstAnswer.click();
        logMessage(": Elso lehetoseg kivalasztasa.");
    }

    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}