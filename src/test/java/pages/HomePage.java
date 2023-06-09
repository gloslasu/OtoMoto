package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

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

    private void carSearch(){
        carMarkClick();
        selectSuzukiMark();
    }

}
