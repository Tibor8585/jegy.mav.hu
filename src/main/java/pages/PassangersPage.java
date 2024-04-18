package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PassangersPage {
    private final WebDriver driver;
    private final By offersButtonBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-purchase/div/div/aside/app-status-line/app-desktop-status-line/div[1]/app-desktop-status-line-step-frame");
    private final By nameFieldBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-purchase/div/div/div[2]/app-router-outlet-adapter/span/app-step-passengers/main/div[3]/div/app-step-passengers-passenger-box/form/div/app-step-passengers-passenger-box-inputs/div/div[1]/app-input/input");
    private final By BODFieldBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-purchase/div/div/div[2]/app-router-outlet-adapter/span/app-step-passengers/main/div[3]/div/app-step-passengers-passenger-box/form/div/app-step-passengers-passenger-box-inputs/div/div[2]/button/span");
    private final By nextButtonBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-purchase/div/div/div[2]/app-router-outlet-adapter/span/app-step-passengers/main/div[4]/button");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PassangersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void offersMenuButton() {
        WebElement offersButton = driver.findElement(offersButtonBy);
        offersButton.click();
        logMessage(": Ajanlatok menupontra kattintas.");
    }

    public void passangerDataField(String name, String BOD) {
        WebElement nameField = driver.findElement(nameFieldBy);
        nameField.click();
        nameField.sendKeys(name);
        WebElement BODField = driver.findElement(BODFieldBy);
        BODField.click();
        WebElement BODFieldFill = driver.switchTo().activeElement();
        BODFieldFill.sendKeys(BOD);
        WebElement pipeSign = driver.switchTo().activeElement();
        pipeSign.click();
        logMessage(": Utas adatok kitoltese");
    }

    public void nextButtonClick() {
        WebElement nextButton = driver.findElement(nextButtonBy);
        nextButton.click();
        logMessage(": A 'kovetkezo' gombra kattintas");
    }

    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}