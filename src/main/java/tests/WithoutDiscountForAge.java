package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.TrainPage;

public class WithoutDiscountForAge extends DriverFactory {

    @Test
    @Description("JHT-6 - A teszt azt vizsgálja, hogy a kedzvezményre nem jogosult utas, valóban a teljes összeget fizeti-e")
    public void withoutDiscount() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
       // homePage.acceptTempPopupWindow();
        homePage.acceptCookies();
        homePage.clickToProfile();
        homePage.clickLoginButton();
        Assert.assertTrue(driver.getPageSource().contains("button blue"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();

        // Belépés utáni rész
        // homePage.acceptTempPopupWindow();
        homePage.addStartStation("Szeged-Rokus");
        homePage.addEndStation("Szentes");
        homePage.clickToSelectDate();
        homePage.selectTravelDate();
        homePage.clickToSearchButton();

        TrainPage trainPage = new TrainPage(driver);
        trainPage.firstAnswer();
        String fullPrice = trainPage.getFullPrice();
        Assert.assertEquals(fullPrice, "1 120 Ft");
    }
}
