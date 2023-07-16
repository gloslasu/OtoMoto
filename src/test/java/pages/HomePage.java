package pages;

import driver.DriverSetup;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.DriverSetup.getDriver;
import static utils.propeties.PropertiesSetup.getBaseOtoMotoUrl;

public class HomePage extends BasePage{

    @FindBy (css = "input[placeholder='Marka pojazdu']")
    private WebElement carMark;

    @FindBy (xpath = "//span[contains(text(), 'Suzuki')]")
    private WebElement suzuki;

    private void carMarkClick(){
        carMark.click();
    }

    private void selectSuzukiMark(){
        suzuki.click();
    }

    @Step
    public void openMainPage() {
        DriverSetup.getDriver().navigate().to("google.pl");
    }

    public void carSearch(){
        carMarkClick();
        selectSuzukiMark();
    }

}
