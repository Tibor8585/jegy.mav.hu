package tests;

import common.DriverFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class chooseMeansOfTransportTrain extends DriverFactory {
    @Test
    @Description("JHT-24 -Vasúti közlekedési eszköz kiválasztásának tesztje")
    public void chooseOfMeansOfTransportTrain () {
        driver.get("https://jegy.mav.hu/");
        HomePage homepage = new HomePage(driver);
        homepage.acceptTempPopupWindow();
        homepage.acceptCookies();
        homepage.selectMeansOfTransport();
        homepage.selectSuburban();
        homepage.meansOfTransportAcceptButton();
        homepage.numberOfLogos();
        Assert.assertEquals(homepage.numberOfLogos(), 1);

    }
}
