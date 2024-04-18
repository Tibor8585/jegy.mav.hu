package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OffersPage {
    private final WebDriver driver;
    private final By offerMenuButtonBy = By.xpath("//*[@id=\"sidenav-container\"]/mat-sidenav-content/app-router-outlet-adapter/span/app-purchase/div/div/aside/app-status-line/app-desktop-status-line/div[1]/app-desktop-status-line-step-frame/div[2]/a");
    private final By choosenPriceBy = By.className("ticket-button");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void offerMenuButton() {
        WebElement offerMenuButton = driver.findElement(offerMenuButtonBy);
        offerMenuButton.click();
        logMessage(": Ajanlatok menupontra kattintas.");
    }

    public void choosePrice() {
        List<WebElement> choosenPrice = driver.findElements(choosenPriceBy);
        choosenPrice.get(0).click();
        logMessage(": Elso lehetoseg kivalasztasa.");
    }

    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}