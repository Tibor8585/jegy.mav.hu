package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProfilePage {
    private final WebDriver driver;
    private final By createTravellerBy = By.xpath("//*[@id=\"saved-passengers\"]/app-saved-passengers/div[1]/div[2]/app-add-passenger-button[1]/button");
    private final By birthdayDateBy = By.className("birthday-trigger");
    private final By saveTravellerBy = By.xpath("//button[contains(@class,'add-button-top')]");
    private final By logoButtonBy = By.id("mav-logo");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateTravellerButton() {
        WebElement createTravellerButton = driver.findElement(createTravellerBy);
        createTravellerButton.click();
        logMessage(": Utas letrehozasa gombra kattintas.");
    }

    public void travellerNameInput(String name) {
        WebElement travellerNameInput = driver.switchTo().activeElement();
        travellerNameInput.clear();
        travellerNameInput.sendKeys(name);
        logMessage(": Uj utas nevenek beirasa.");
    }

    public void fillBirthdayDate(String date) {
        WebElement birthdayInputButton = driver.findElement(birthdayDateBy);
        birthdayInputButton.click();
        WebElement birthdayInput = driver.switchTo().activeElement();
        birthdayInput.sendKeys(date);
        logMessage(": Utas szuletesi idejenek beirasa.");
    }

    public void saveTraveller() throws InterruptedException {
        driver.findElement(saveTravellerBy).click();
        Thread.sleep(500);
        driver.findElement(By.className("passenger-save")).click();
        logMessage(": Uj utas elmentese.");
    }

    public boolean doesPassengerBoxExist() {
        return driver.findElements(By.className("passenger-box")).isEmpty();
    }

    public String travellerName() {
        return driver.findElement(By.xpath("//h3")).getText();
    }

    public void deletePassengers() throws InterruptedException {
        List<WebElement> passengerTrashBinIcons = driver.findElements(By.className("passenger-delete"));
        for (WebElement trash : passengerTrashBinIcons) {
            trash.click();
            Thread.sleep(1000);
            driver.findElement(By.id("delete-passenger-button")).click();
            logMessage(": Utas torlese.");
            Thread.sleep(1000);
        }
    }

    public boolean arePassengerPresent(String className) {
        logMessage(": Utasok letezesenek vizsgalata.");
        try {
            driver.findElement(By.className(className));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void createTraveller(String name, String date) throws InterruptedException {
        clickCreateTravellerButton();
        travellerNameInput(name);
        fillBirthdayDate(date);
        Thread.sleep(1000);
        saveTraveller();
        Thread.sleep(1000);
        logMessage(": Utas letrehozasa");
    }

    public int getNumberOfTravellers() {
        logMessage(": Utasok megszamolasa.");
        return driver.findElements(By.className("passenger-box")).size();
    }

    public void backToHomePage() {
       WebElement logoButton = driver.findElement(logoButtonBy);
       logoButton.click();
       logMessage(": Viszatérés a főoldalra.");
    }

    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}
