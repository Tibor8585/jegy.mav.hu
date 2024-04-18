package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.TrainPage;

public class DiscountsForAge extends DriverFactory {
    @Test
    @Description("JHT-7 - A teszteset azt vizsgálja, hogy a kiválasztott életkorú utas a megfelelő kedvezményben részesül.")
    public void fiftyPercentDiscount() throws InterruptedException {
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
        homePage.selectHungarianLanguage();
        homePage.addStartStation("Szeged-Rokus");
        homePage.addEndStation("Szentes");
        homePage.clickToSelectDate();
        homePage.selectTravelDate();
        homePage.clickToAddPessengersAndDiscountsButton();
        homePage.clickToAddpessengerButton();
        homePage.clickToPassengerButton();
        homePage.selectAgeOfPassenger();
        homePage.clickToAcceptPassengerButton();
        homePage.clickToSearchButton();

        TrainPage trainPage = new TrainPage(driver);
        trainPage.firstAnswer();
        String discountPrice = trainPage.getLowestPrice();
        String fullPrice = trainPage.getHighestPrice();
        Assert.assertEquals(discountPrice, "560 Ft");
        Assert.assertEquals(fullPrice, "1 120 Ft");
    }

}
