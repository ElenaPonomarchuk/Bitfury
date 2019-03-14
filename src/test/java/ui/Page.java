package ui;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static net.sourceforge.htmlunit.cyberneko.HTMLEntities.get;

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
    @FindBy(xpath = "//p[1]")
    private WebElement wikiDescription;

    public void openURL(){
        open();
    }

    public void voiteEllectionBtnClick() {
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

    public void getDescription() {
        officialCandidatePage.click();
        ArrayList<String> tabs2 = new ArrayList<String> (getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(1));
        String text = wikiDescription.getText();
        System.out.println(text);

//        String winHandleBefore = getDriver().getWindowHandle();

    }
}
