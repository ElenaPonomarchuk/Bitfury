package ui;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class eVotingPage extends PageObject {
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
    @FindBy (xpath = "//div[@class='confirm-choise-block-title']")
    private WebElement confirmText;
    @FindBy (xpath = "//div[@class='confirm-choise-block-name ng-binding']")
    private WebElement candidateName;
    @FindBy (xpath = "//div[@class='button button-red' and text()='YES']")
    private WebElement yesBtn;
    @FindBy (xpath = "//div[@class='button button-green' and text()='SIGN']")
    private WebElement signBtn;
    @FindBy (xpath = "//div[text()='SIGN BALLOT']")
    private WebElement signBallotBtn;
    @FindBy (xpath = "//span[@class='ng-binding']")
    private WebElement BallotSignetPage;
    @FindBy (xpath = "//div[text()='SUBMIT BALLOT']")
    private WebElement ballotSubmitBtn;
    @FindBy (xpath = "//div[text()='DISCARD BALLOT']")
    private WebElement discartBallotBtn;
    @FindBy (xpath = "//input[@placeholder='Your mail']")
    private WebElement emailInputField;
    @FindBy (xpath = "//div[@class='code-box code-box-bigger ng-scope ng-binding']")
    private WebElement wordMemo;
    @FindBy (xpath = "//div[@class='code-box ng-scope ng-binding']")
    private WebElement hash;

    public void openURL(){
        open();
    }

    public void voteElectionBtnClick() {
        voiteEllectionBtn.click();
    }
    public void selectEstonianPresidential() {
        estonianPresidantial.click();
    }

    public void selectCandidate(String candidate){
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

    public void checkVoteElectionPopUp(String candidate){
      String confirmTextText = confirmText.getText();
      System.out.println(confirmTextText);
      assertThat(confirmTextText, equalTo("Are you sure you want to prepare\n" +
              "an anonymous ballot with\n" +
              "following candidate selected?"));
      assertThat(candidateName.getText(), equalTo(candidate));
      getDriver().findElement(By.xpath("//div[@class='button button-red' and text()='YES']")).isDisplayed();
      getDriver().findElement(By.xpath("//div[@class='button' and text()='CANCEL']")).isDisplayed();
    }

    public void confirmCandidateSelected(){
        yesBtn.click();
    }

    public void checkBallotReceiptPage(){
        getDriver().findElement(By.xpath("//span[text()='Ballot Receipt']/ancestor::div[@class='app-content-header ng-scope']")).isDisplayed();
        getDriver().findElement(By.xpath("//div[@class='code-box ng-scope ng-binding']")).isDisplayed();
        getDriver().findElement(By.xpath("//div[@class='button' and text()='Save 3-word memo and ballot hash']")).isDisplayed();
        getDriver().findElement(By.xpath("//div[text()='DISCARD']")).isDisplayed();
        getDriver().findElement(By.xpath("//div[text()='DECRYPT']")).isDisplayed();
        getDriver().findElement(By.xpath("//div[text()='SIGN']")).isDisplayed();
    }

    public void signBallotReceipt(){
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", signBtn);
        waitABit(3000);
        signBtn.click();
    }

    public void setValidUniquePin(){
        String uniqueNumber = ""+((int)(Math.random()*9000)+1000);
        System.out.println(uniqueNumber);
        for(int i = 0; i < uniqueNumber.length(); i++) {
            char charAtZero = uniqueNumber.charAt(i);
            System.out.println(charAtZero);
            waitFor(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='keyboard-button-digit' and text()='" + charAtZero + "']/ancestor::div[contains(@class,'keyboard-button')])[2]")));
            getDriver().findElement(By.xpath("(//div[@class='keyboard-button-digit' and text()='" + charAtZero + "']/ancestor::div[contains(@class,'keyboard-button')])[2]")).click();
            }
        getDriver().findElement(By.xpath("//div[text()='SIGN BALLOT']")).click();
        }

        public void checkBallotSignetPageAppears(){
        getDriver().findElement(By.xpath("//span[@class='ng-binding' and text()='Ballot has been signed']")).isDisplayed();
        }

    public void checkBallotSignedPage(){
        emailInputField.isDisplayed();
        ballotSubmitBtn.isDisplayed();
        discartBallotBtn.isDisplayed();
    }

    public void setEmail(String email){
        emailInputField.sendKeys(email);
        ballotSubmitBtn.click();
        waitABit(60000); // Need to wait until the letter arrives at the mail we have entered
    }

    public void getHashAndMemo(){
        Serenity.setSessionVariable("hashText").to(hash.getText());
        System.out.println("HASH IS HERE:" +Serenity.sessionVariableCalled("hashText").toString());
        Serenity.setSessionVariable("memoText").to(wordMemo.getText());
        System.out.println("MEMO IS HERE:"+ Serenity.sessionVariableCalled("memoText").toString());
    }
}

