package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final By popupAcceptButtonBy = By.className("test-helper-confirm-yes");
    private final By acceptButtonBy = By.xpath(("//button[contains(@class,'bg-blue color-white ng-star-inserted')]"));
    private final By acceptCookiesButtonBy = By.xpath(("//button[contains(@class,'bg-blue color-white ng-star-inserted')]"));
    private final By clickToProfileButtonBy = By.id("profile-popup-button");
    private final By clickToLoginButtonBy = By.id("login");
    private final By clickProfileFromDropdown = By.id("profile-button");
    private final By selectHungarianLanguageBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/app-header/header/div/div/ul/li[2]/select");
    private final By startStationFieldBy = By.id("startStation-input");
    private final By endStationFieldBy = By.id("endStation-input");
    private final By dateSelectorBy = By.id("_unique-runtime-id-2");
    private final By selectTravelDateBy = By.xpath("/html/body/div[2]/div/div/div/app-datepicker/div/table/tbody/tr[5]/td[2]");
    private final By addPassengersAndDiscountsButtonBy = By.id("passengers-button");
    private final By addPassengerButtonBy = By.cssSelector("button[aria-label='+ Utas']");
    private final By passengerButtonBy = By.xpath("/html/body/div[2]/div/div/div/app-passengers-settings/div[2]/div[1]/div/div[3]/app-passenger-settings-passenger-element/div/div[1]/div[1]/button");
    private final By ageSelectorBy = By.xpath("/html/body/div[2]/div[2]/div/app-passengers-settings-add-passenger-list/div[3]/div/div/button[5]");
    private final By childAgeSelectorBy = By.xpath("/html/body/div[2]/div[2]/div/app-passengers-settings-add-passenger-list/div[3]/div/div/button[2]");
    private final By acceptPassengerButtonBy = By.id("passengers-settings-accept-button");
    // private final By searchButtonBy = By.xpath("//button[contains(@class,'button yellow search-button d-flex justify-content-center align-items-center')]"); //kikommenteltem, hogy lefusson a tesztem
    private final By searchButtonBy = By.xpath("//button[contains(@class,'search-button')]");
    private final By meansOfTransportFieldBy = By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-home/div/app-head-search/section/div/div[1]/div/div/div/app-head-search-normal-loader/app-head-search-normal/section/div/div/form/div/div/app-head-search-modality/section/span");
    private final By trainCheckBoxBy = By.id("modCheckbox0");
    private final By busCheckBoxBy = By.id("modCheckbox1");
    private final By suburbanCheckBoxBy = By.id("modCheckbox2");
    private final By submitButtonBy = By.cssSelector("button[class ='button yellow']");
    private final By feedbackButtonBy = By.xpath("//*[@id='sidenav-container']/mat-sidenav-content/app-footer/footer/div/div[2]/app-footer-feedback-section/button");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptTempPopupWindow() {
        WebElement tempPopupAcceptButton = driver.findElement(popupAcceptButtonBy);
        tempPopupAcceptButton.click();
        logMessage(": Felugro popup ablak elfogadasa.");
    }

    public void acceptCookies() {
        WebElement acceptCookiesButton = driver.findElement(acceptButtonBy);
        acceptCookiesButton.click();
        logMessage(": Cookiek elfogadasa.");
    }

    public void clickToProfile() throws InterruptedException {
        Thread.sleep(4000);
        WebElement profileButton = driver.findElement(clickToProfileButtonBy);
        Thread.sleep(1000);
        profileButton.click();
        logMessage(": Profil menupontra kattintas.");
    }

    public void clickLoginButton() throws InterruptedException {
        WebElement loginButton = driver.findElement(clickToLoginButtonBy);
        Thread.sleep(1000);
        loginButton.click();
        logMessage(": Belepes gombra kattintas.");
    }

    public void openProfileFromDropdown() {
        WebElement profileFromDropdown = driver.findElement(clickProfileFromDropdown);
        profileFromDropdown.click();
        logMessage(": Profil megnyitasa a lenyilo menubol.");

    }

    public void selectHungarianLanguage() {
        Select languageDropdown = new Select(driver.findElement(selectHungarianLanguageBy));
        languageDropdown.selectByVisibleText("hu");
        logMessage(": Magyar nyelv kivalasztasa.");

    }

    public void selectMeansOfTransport() {
        WebElement meansOfTransportField = driver.findElement(meansOfTransportFieldBy);
        meansOfTransportField.click();
        logMessage(": Utazasi eszkozok kivalasztasa.");
    }

    public void selectTrain() {
        WebElement trainCheckBox = driver.findElement(trainCheckBoxBy);
        trainCheckBox.click();
        logMessage(": Vonat kivalasztasa.");
    }

    public void selectBus() {
        WebElement busCheckBox = driver.findElement(busCheckBoxBy);
        busCheckBox.click();
        logMessage(": Bus kivalasztasa.");
    }

    public void selectSuburban() {
        WebElement suburbanCheckBox = driver.findElement(suburbanCheckBoxBy);
        suburbanCheckBox.click();
        logMessage(": HEV kivalasztasa.");
    }

    public void acceptButton() {
        List<WebElement> acceptButton = driver.findElements(acceptButtonBy);
        acceptButton.get(2).click();
        logMessage(": Elfogadas gombra kattintas.");
    }

    //Belépés utáni rész

    public void addStartStation(String startStation) throws InterruptedException {
        WebElement startStationField = driver.findElement(startStationFieldBy);
        startStationField.sendKeys(startStation);
        Thread.sleep(2000);
        startStationField.sendKeys(Keys.ENTER);
        logMessage(": Indulasi allomas megadasa.");
    }

    public void addEndStation(String endStation) throws InterruptedException {
        WebElement startStationField = driver.findElement(endStationFieldBy);
        startStationField.sendKeys(endStation);
        Thread.sleep(2000);
        startStationField.sendKeys(Keys.ENTER);
        logMessage(": Celallomas megadasa.");
    }

    public void clickToSelectDate() throws InterruptedException {
        WebElement clickToDateSelector = driver.findElement(dateSelectorBy);
        Thread.sleep(1000);
        clickToDateSelector.click();
        logMessage(": Datum menupontra kattintas.");
    }

    public void selectTravelDate() throws InterruptedException {
        WebElement selectTravelDate = driver.findElement(selectTravelDateBy);
        Thread.sleep(1000);
        selectTravelDate.click();
        logMessage(": Utazasi datum megadasa.");
    }

    public void clickToAddPessengersAndDiscountsButton() throws InterruptedException {
        WebElement addPessengersAndDiscountsButton = driver.findElement(addPassengersAndDiscountsButtonBy);
        Thread.sleep(1000);
        addPessengersAndDiscountsButton.click();
        logMessage(": Utasok es kedvezmenek menupontra kattintas.");
    }

    public void clickToAddpessengerButton() throws InterruptedException {
        WebElement addPessengerButton = driver.findElement(addPassengerButtonBy);
        Thread.sleep(1000);
        addPessengerButton.click();
        logMessage(": Utas hozzaadasa gombra kattintas.");
    }

    public void clickToPassengerButton() throws InterruptedException {
        WebElement clickToPassengerButton = driver.findElement(passengerButtonBy);
        Thread.sleep(1000);
        clickToPassengerButton.click();
        logMessage(": Utas kivalasztasa.");
    }

    public void selectAgeOfPassenger() throws InterruptedException {
        WebElement selectAge = driver.findElement(ageSelectorBy);
        Thread.sleep(1000);
        selectAge.click();
        logMessage(": Eletkor kategoria kivalasztasa.");
    }

    public void selectAgeOfChild() throws InterruptedException {
        WebElement selectChildAge = driver.findElement(childAgeSelectorBy);
        Thread.sleep(1000);
        selectChildAge.click();
        logMessage(": Gyermek eletkor kivalasztasa.");
    }

    public void clickToAcceptPassengerButton() throws InterruptedException {
        WebElement acceptPassengerButton = driver.findElement(acceptPassengerButtonBy);
        Thread.sleep(1000);
        acceptPassengerButton.click();
        logMessage(": Rendben gombra kattintas.");
    }

    public void clickToSearchButton() throws InterruptedException {
        WebElement searchButton = driver.findElement(searchButtonBy);
        Thread.sleep(1000);
        searchButton.click();
        logMessage(": Kereses gombra kattintas.");
    }

    public void clickOnFeedbackButton() {
        WebElement feedbackButton = driver.findElement(feedbackButtonBy);
        feedbackButton.click();
        logMessage(": Visszajelzes menupont kivalasztasa.");
    }

    public void meansOfTransportAcceptButton() {
        List<WebElement> submitButton = driver.findElements(submitButtonBy);
        submitButton.get(0).click();
        logMessage(": Utazasi modok elfogadasa gombra kattintas.");
    }

    public String activeProfileIcon() {
        logMessage(": Bejelentkezett felhasznalo profil ikonjank CSS ertek lementese.");
        return driver.findElement(By.className("profile-image")).getCssValue("background-image");
    }

    public int numberOfLogos() {
        WebElement parentElement = driver.findElement(By.xpath("/html/body/app-root/app-route-shell-with-sidenav/mat-sidenav-container/mat-sidenav-content/app-router-outlet-adapter/span/app-home/div/app-head-search/section/div/div[1]/div/div/div/app-head-search-normal-loader/app-head-search-normal/section/div/div/form/div/div/app-head-search-modality/section/span/div"));
        List<WebElement> allChildElements = parentElement.findElements(By.xpath("*"));
        logMessage(": Logok szamanak meghatarozasa.");
        return allChildElements.size();
    }


    private void logMessage(String message) {
        LocalDateTime logDT = LocalDateTime.now();
        String formattedDateTime = logDT.format(formatter);
        System.out.println("[" + formattedDateTime + "] " + message);
    }
}