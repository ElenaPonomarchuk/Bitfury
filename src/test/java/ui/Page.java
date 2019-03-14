package ui;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class Page extends PageObject {
    @FindBy(xpath = "//div[text()='VOTE IN ELECTION']")
    private WebElement voiteEllectionBtn;
    @FindBy(xpath = "//td[text()='Estonian Presidential Election']/ancestor::tr/descendant::div")
    private WebElement estonianPresidantial;
    @FindBy(xpath = "//td[text()='Eiki Nestor']/ancestor::tr/descendant::div")
    private WebElement einkiNestor;
    @FindBy(xpath = "//div[@class='list-option-description ng-binding']")
    private WebElement shortDescription;
    @FindBy(xpath = "//a[text()='Official candidate page']")
    private WebElement officialCandidatePage;



    public void voiteEllectionBtnClick() {
        open();
        voiteEllectionBtn.click();
    }
    public void selectEstonianPresidantial() {
        estonianPresidantial.click();
    }
    public void selectCandidat(){
        einkiNestor.click();
    }
//    public void assertDescription(){
//        shortDescription.getText(), equals();
//    }
    public void getDescription(){
        officialCandidatePage.click();

        String winHandleBefore = getDriver().getWindowHandle();

    }
}
