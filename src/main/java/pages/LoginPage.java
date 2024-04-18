package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginPage {
    private final WebDriver driver;
    private final By emailInputBy = By.id("emailFie");
    private final By passwordInputBy = By.id("passwordFie");
    private final By loginButtonBy = By.id("login-btn");
    private final By guestEmailFieldBy = By.id("guest-emailFie");
    private final By guestEmailFieldAgainBy = By.id("guest-email-againFie");
    private final By acceptDataBy = By.id("acceptData");
    private final By acceptTermsBy = By.id("acceptTerms");
    private final By guestLoginBy = By.id("guest-login");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillEmailInput(String email) {
        WebElement emailInput = driver.findElement(emailInputBy);
        emailInput.sendKeys(email);
        logMessage(": E-mail cim megadasa.");
    }

    public void fillPasswordInput(String password) {
        WebElement passwordInput = driver.findElement(passwordInputBy);
        passwordInput.sendKeys(password);
        logMessage(": Jelszo megadasa.");
    }

    public void clickLoginButton() throws InterruptedException {
        WebElement loginButton = driver.findElement(loginButtonBy);
        Thread.sleep(1000);
        loginButton.click();
        logMessage(": Bejelentkezes gombra kattintas.");
    }

    public void buyingAsGuest(String email) {
        WebElement guestEmailField = driver.findElement(guestEmailFieldBy);
        guestEmailField.sendKeys(email);
        WebElement guestEmailFieldAgain = driver.findElement(guestEmailFieldAgainBy);
        guestEmailFieldAgain.sendKeys(email);
        logMessage(": Vendegkent email megadasa.");
    }

    public void checkboxesAccepting() {
        WebElement acceptData = driver.findElement(acceptDataBy);
        acceptData.click();
        WebElement acceptTerms = driver.findElement(acceptTermsBy);
        acceptTerms.click();
        WebElement guestLogin = driver.findElement(guestLoginBy);
        guestLogin.click();
        logMessage(": Checkboxok pipa es folytatas vendegkent.");
    }

    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}