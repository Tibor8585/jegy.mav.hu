package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfilePageTests extends DriverFactory {

    @Test
    @Description("JHT-38 - Teszteset, ami egy 25 év feletti utas sikeres hozzáadását vizsgálja.")
    public void addTravellerMoreThan25Years() throws InterruptedException {
        driver.get("https://jegy.mav.hu/bejelentkezes");
        String loginURL = driver.getCurrentUrl();
        Assert.assertEquals(loginURL, "https://jegy.mav.hu/bejelentkezes", "Nem a megfelelő oldal töltött be.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.activeProfileIcon().equals("url(\"https://jegy.mav.hu/assets/images/icon-profile.svg\")"));
        homePage.acceptCookies();
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
    }

    @Test
    @Description("JHT-49 - Teszteset, ami az előzőleg létrehozott utasok törlését vizsgálja.")
    public void deleteAllPassengers() throws InterruptedException {
        driver.get("https://jegy.mav.hu/bejelentkezes");
        String loginURL = driver.getCurrentUrl();
        Assert.assertEquals(loginURL, "https://jegy.mav.hu/bejelentkezes", "Nem a megfelelő oldal töltött be.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.activeProfileIcon().equals("url(\"https://jegy.mav.hu/assets/images/icon-profile.svg\")"));
        Thread.sleep(2000);
        homePage.acceptCookies();
        homePage.selectHungarianLanguage();
        homePage.clickToProfile();
        homePage.openProfileFromDropdown();

        ProfilePage profilePage = new ProfilePage(driver);
        Thread.sleep(1000);
        String profileURL = driver.getCurrentUrl();
        Assert.assertEquals(profileURL, "https://jegy.mav.hu/profil");
        profilePage.deletePassengers();
        Thread.sleep(1500);
        Assert.assertFalse(profilePage.arePassengerPresent("passenger-content"), "Sikertelen tesztfuttatas. Nem lett minden utas torolve.");
    }

    @Test
    @Description("JHT-48 - Teszteset, amely 3 különböző életkorú utas létrehozása")
    public void createThreePassengers() throws InterruptedException {
        driver.get("https://jegy.mav.hu/bejelentkezes");
        String loginURL = driver.getCurrentUrl();
        Assert.assertEquals(loginURL, "https://jegy.mav.hu/bejelentkezes", "Nem a megfelelő oldal töltött be.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.activeProfileIcon().equals("url(\"https://jegy.mav.hu/assets/images/icon-profile.svg\")"));
        Thread.sleep(2000);
        homePage.acceptCookies();

        homePage.selectHungarianLanguage();
        homePage.clickToProfile();
        homePage.openProfileFromDropdown();
        ProfilePage profilePage = new ProfilePage(driver);
        Thread.sleep(1500);
        String profileURL = driver.getCurrentUrl();
        Assert.assertEquals(profileURL, "https://jegy.mav.hu/profil");

        profilePage.createTraveller("Kis Balta", "2015. 01. 01");
        profilePage.createTraveller("Kozepes Balta", "2008. 01. 01");
        profilePage.createTraveller("Nagy Balta", "1990. 01. 01");

        Thread.sleep(5000);
        Assert.assertEquals(profilePage.getNumberOfTravellers(), 3, "Nem 3 létrejött utasunk van.");
    }
}
