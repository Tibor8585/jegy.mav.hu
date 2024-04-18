package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.TrainPage;

public class FullDiscountForAge extends DriverFactory {

    @Test
    @Description("JHT-8 - A teszt azt vizsgálja, hogy a díjmentes utazásra jogosult utasnak 100%-os kedvezményt számol-e fel")
    public void fullDiscount() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
        //  homePage.acceptTempPopupWindow();
        homePage.acceptCookies();
        homePage.clickToProfile();
        homePage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();
        Assert.assertTrue(driver.getPageSource().contains("button blue"));

        // Belépés utáni rész
        // homePage.acceptTempPopupWindow();
        homePage.addStartStation("Szeged-Rokus");
        homePage.addEndStation("Szentes");
        homePage.clickToSelectDate();
        homePage.selectTravelDate();
        homePage.clickToAddPessengersAndDiscountsButton();
        homePage.clickToAddpessengerButton();
        homePage.clickToPassengerButton();
        homePage.selectAgeOfChild();
        homePage.clickToPassengerButton();
        homePage.clickToAcceptPassengerButton();
        homePage.clickToSearchButton();

        TrainPage trainPage = new TrainPage(driver);
        trainPage.firstAnswer();
        String freePrice = trainPage.getFreePrice();
        Assert.assertEquals(freePrice, "0 Ft");
    }
}
