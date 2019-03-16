package ui;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
    @FindBy(xpath ="//h1[@id='firstHeading']")
    private WebElement candidateHeader;


    String twoSentences;

    public void openURL(){
        open();
    }

    public void voiteEllectionBtnClick() {
        voiteEllectionBtn.click();
    }
    public void selectEstonianPresidantial() {
        estonianPresidantial.click();
    }

    public void selectCandidat(String candidate){
        getDriver().findElement(By.xpath("//td[text()='"+ candidate +"']/ancestor::tr/descendant::div")).click();
    }

    public void openOfficialCandidatPage(){
        officialCandidatePage.click();
    }

    public String getWikiText() {
        String[] parts = wikiDescription.getText().split("\\.");
        return  parts[0] + "." + parts[1] + ".";

    }

    public void changeBrowserTab(int tab){
        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(tab));
    }

    public void checkCandidateDescription(String wikiText){
        assertThat(shortDescription.getText(), equalTo(wikiText));
    }

    public void checkCandidatesQuantity(){
        assertThat(getDriver().findElements(By.xpath("//tr[@ng-repeat='candidate in currentElection.candidates']")).size(), is(4));
    }

    public void checkCandidateLink(String candidateName){
        assertThat(candidateHeader.getText(), equalTo(candidateName));
    }


    }

