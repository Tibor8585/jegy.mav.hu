package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class ChooseMeansOfTransportTest extends DriverFactory {

    @Test
    @Description("JHT-34 - Közlekedési eszköz kiválasztásának tesztje")
    public void chooseOfMeansOfTransport() throws InterruptedException {
        driver.get("https://jegy.mav.hu/");
        HomePage homePage = new HomePage(driver);
        homePage.acceptTempPopupWindow();
        homePage.acceptCookies();
        homePage.selectMeansOfTransport();
        homePage.selectBus();
        homePage.meansOfTransportAcceptButton();
        homePage.numberOfLogos();
        Assert.assertEquals(homePage.numberOfLogos(), 3);
    }
}
