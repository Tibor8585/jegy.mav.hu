package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FeedbackPage {

    private final WebDriver driver;
    private final By categoryDropdownlistBy = By.cssSelector("select[aria-label=category]");
    private final By userEnvironmentInputFieldBy = By.id("user-environment");
    private final By detailedDescriptionFieldBy = By.id("description");
    private final By sendButtonBy = By.xpath("//*[@id=\"sidenav-container\"]/mat-sidenav-content/app-router-outlet-adapter/span/app-feedback/main/section/div/div[1]/form/div[4]/button");
    private final By feedbackTitleBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-feedback/main/h1");
    private final By feedbackTitle2By = By.className("beta-main-title");
    private final By getFeedbackTitle3By = By.xpath("//*[@id=\"sidenav-container\"]/mat-sidenav-content/app-router-outlet-adapter/span/app-feedback/main/h1");
    private final By bigLogoBy = By.className("success-icon-beta");
    private final By errorMessageBy = By.id("categoryErrorLabel");
    private final By errorMessageEnvironmentFieldBy = By.id("userEnvironmentErrorLabel");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public FeedbackPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCategoryDropdownList() {
        WebElement categoryDropdownList = driver.findElement(categoryDropdownlistBy);
        categoryDropdownList.click();
        logMessage(": Kattintas a lenyilo listara.");
    }

    public void selectFeedbackCategory(String option) {
        WebElement selectFeedbackCategory = driver.findElement(categoryDropdownlistBy);
        Select select = new Select(selectFeedbackCategory);
        select.selectByValue(option);
        logMessage(": Visszajelzes kategoriajanak kivalasztasa.");
    }

    public void clickInUserEnvironmentInputField() {
        WebElement userEnvironmentInputField = driver.findElement(userEnvironmentInputFieldBy);
        userEnvironmentInputField.click();
        logMessage(": Felhasznaloi kornyezet kitoltese.");
    }

    public void fillUserEnvironmentInputField(String environment) {
        WebElement userEnvironmentInputField = driver.findElement(userEnvironmentInputFieldBy);
        userEnvironmentInputField.click();
        userEnvironmentInputField.sendKeys(environment);
        logMessage(": Felhasznaloi kornyezet kitoltese.");
    }

    public void fillDetailedDescriptionInputField(String text) {
        WebElement detailedDescriptionField = driver.findElement(detailedDescriptionFieldBy);
        detailedDescriptionField.click();
        detailedDescriptionField.sendKeys(text);
        logMessage(": Reszletes leiras mezo kitoltese");
    }

    public void clickOnSendButton() {
        WebElement sendButton = driver.findElement(sendButtonBy);
        sendButton.click();
        logMessage(": Kuldes gombra kattintas.");
    }

    public String getThankYouForYourFeedbackTitle() {
        //List<WebElement> title = driver.findElements(feedbackTitleBy);
        //return title.get(0).getText();
        WebElement title = driver.findElement(feedbackTitleBy);
        return title.getText();
    }

    public WebElement getMAVLogo() {
        WebElement bigLogo = driver.findElement(bigLogoBy);
        logMessage(": MAV logo keresese.");
        return bigLogo;
    }

    public String getEmptyCategoryFieldErrorMessage() {
        WebElement errorMessage = driver.findElement(errorMessageBy);
        logMessage(": Ures kategoria mezo hibauzenet megkeresese.");
        return errorMessage.getText();
    }

    public boolean isSendButtonEnabled() {
        WebElement sendButton = driver.findElement(sendButtonBy);
        logMessage(": Ellenorzes, hogy a Kuldes gomb aktiv-e.");
        return sendButton.isEnabled();
    }

    public String getEmptyUserEnvironmentFieldErrorMessage() {
        WebElement errorMessageEnvironmentField = driver.findElement(errorMessageEnvironmentFieldBy);
        logMessage(": Ures kategoria mezo hibauzenet megkeresese.");
        return errorMessageEnvironmentField.getText();
    }

    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}
