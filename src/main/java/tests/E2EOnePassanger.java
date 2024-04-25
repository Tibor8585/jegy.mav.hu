package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class E2EOnePassanger extends DriverFactory {
    @Test
    @Description("JHT-38 - Teszteset, 1 utas E2E tesztje, az utas létrehozásátról a fizetésig")
    public void E2EOnePassangerCreate() throws InterruptedException {
        driver.get("https://jegy.mav.hu/bejelentkezes");
        String loginURL = driver.getCurrentUrl();
        Assert.assertEquals(loginURL, "https://jegy.mav.hu/bejelentkezes", "Nem a megfelelő oldal töltött be.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("zzoltan539@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        homePage.acceptTempPopupWindow();
        homePage.acceptCookies();
       // Assert.assertTrue(homePage.activeProfileIcon().equals("url(\"https://jegy.mav.hu/assets/images/icon-profile.svg\")"));
        Thread.sleep(4500);
        homePage.selectHungarianLanguage();
        homePage.clickToProfile();
        homePage.openProfileFromDropdown();
        Thread.sleep(1000);

        ProfilePage profilePage = new ProfilePage(driver);
        String profileURL = driver.getCurrentUrl();
        Assert.assertEquals(profileURL, "https://jegy.mav.hu/profil");

        profilePage.clickCreateTravellerButton();
        Assert.assertFalse(profilePage.doesPassengerBoxExist());

        profilePage.travellerNameInput("Nagy Balta");
        profilePage.fillBirthdayDate("1995. 01. 01.");
        profilePage.saveTraveller();

        Thread.sleep(5000);
        Assert.assertEquals(profilePage.travellerName(), "Nagy Balta");

        profilePage.backToHomePage();
    }
}
