package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FeedbackPage;
import pages.HomePage;
import pages.LoginPage;

public class SendingFeedbackTests extends DriverFactory {

    @Test
    @Description("JHT-16 - Visszajelzés küldésének tesztje megfelelő kitöltéssel.")
    public void sendingFeedbackProperFillout() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
        //homePage.acceptTempPopupWindow();
        homePage.acceptCookies();
        Thread.sleep(2000);
        homePage.clickToProfile();
        homePage.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/bejelentkezes", "Nem sikerult betolteni a bejelentkezes oldalt.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();
        homePage.clickOnFeedbackButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/feedback", "Nem sikerult betolteni a feedback oldalt.");
        FeedbackPage feedbackPage = new FeedbackPage(driver);
        feedbackPage.clickOnCategoryDropdownList();
        feedbackPage.selectFeedbackCategory("1");
        feedbackPage.fillUserEnvironmentInputField("Windows, Google Chrome");
        feedbackPage.fillDetailedDescriptionInputField("Ez egy teszt");
        feedbackPage.clickOnSendButton();
        Thread.sleep(2000);
        String title = feedbackPage.getThankYouForYourFeedbackTitle();
        Assert.assertEquals(title, "THANK YOU FOR YOUR FEEDBACK.", "Az elvart uzenet nem jelenik meg.");
        //Assert.assertTrue(feedbackPage.getMAVLogo().isDisplayed(), "Nem sikerult megtalalni a MAV logot.");
    }

    @Test
    @Description("JHT-10 - Visszajelzés küldésének tesztje üres kategória mezővel.")
    public void sendingFeedbackCategoryFieldEmpty() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        Thread.sleep(2000);
        homePage.clickToProfile();
        homePage.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/bejelentkezes", "Nem sikerult betolteni a bejelentkezes oldalt.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();
        homePage.clickOnFeedbackButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/feedback", "Nem sikerult betolteni a feedback oldalt.");
        FeedbackPage feedbackPage = new FeedbackPage(driver);
        feedbackPage.clickOnCategoryDropdownList();
        Assert.assertFalse(feedbackPage.isSendButtonEnabled(), "A kuldes gomb aktiv.");
        feedbackPage.clickOnSendButton();
        String errorMessage = feedbackPage.getEmptyCategoryFieldErrorMessage();
        Assert.assertEquals(errorMessage, "The category field is empty, fill it in, please!", "Nem jelenik meg az elvart uzenet.");
    }

    @Test
    @Description("JHT-13 - Visszajelzés küldésének tesztje üres felhasználói környezet mezővel.")
    public void sendingFeedbackEnvironmentFieldEmpty() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        Thread.sleep(2000);
        homePage.clickToProfile();
        homePage.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/bejelentkezes", "Nem sikerult betolteni a bejelentkezes oldalt.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailInput("kisbaltak@gmail.com");
        loginPage.fillPasswordInput("Iltidazsu1");
        loginPage.clickLoginButton();
        homePage.clickOnFeedbackButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://jegy.mav.hu/feedback", "Nem sikerult betolteni a feedback oldalt.");
        FeedbackPage feedbackPage = new FeedbackPage(driver);
        feedbackPage.clickOnCategoryDropdownList();
        feedbackPage.selectFeedbackCategory("1");
        feedbackPage.clickInUserEnvironmentInputField();
        Assert.assertFalse(feedbackPage.isSendButtonEnabled(), "A kuldes gomb aktiv.");
        feedbackPage.clickOnSendButton();
        String errorMessageEnvironment = feedbackPage.getEmptyUserEnvironmentFieldErrorMessage();
        Assert.assertEquals(errorMessageEnvironment,"The user environment field is empty, fill it in, please!", "Nem jelenik meg az elvart uzenet.");
    }
}
