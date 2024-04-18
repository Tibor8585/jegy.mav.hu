package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class NavigationTestWithLeftSideMenu extends DriverFactory {
    @Test
    @Description("JHT-43 - Teszteset, ami a főoldal bal oldali menüjét vizsgálja")
    public void navigationWithLeftSideMenu() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        // homePage.acceptTempPopupWindow();
        homePage.addStartStation("Erd");
        homePage.addEndStation("Budapest");
        homePage.clickToSearchButton();

        TrainPage trainPage = new TrainPage(driver);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/jegy/vonat");
        trainPage.timeAndRoadMenuButtonClick();

        DetailSearchPage detailSearchPage = new DetailSearchPage(driver);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/jegy/reszletes-kereso");
        detailSearchPage.searchButtonClick();

        trainPage.chooseFirstAnswer();

        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/bejelentkezes?ticketPurchaseInProgress=true&redirect=%2Fjegy%2Fvonat");
        loginPage.buyingAsGuest("kismokus9@gmail.com");
        loginPage.checkboxesAccepting();

        PassangersPage passangersPage = new PassangersPage(driver);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/jegy/utasok");
        passangersPage.offersMenuButton();

        OffersPage offersPage = new OffersPage(driver);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/jegy/utasok");
        offersPage.offerMenuButton();
        offersPage.choosePrice();

        passangersPage.passangerDataField("Kis Baltazar", "03-02-1984");
        passangersPage.nextButtonClick();
    }
}