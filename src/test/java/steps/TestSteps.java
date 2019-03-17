package steps;

import net.thucydides.core.annotations.Step;

import ui.Page;

public class TestSteps {
    Page page;


    @Step
    public void openPresidantialElectionList(){
        page.openURL();
        page.voiteEllectionBtnClick();
    }

    @Step
    public void selectEstonianElection(){
        page.selectEstonianPresidantial();
        page.voiteEllectionBtnClick();
    }

    @Step
    public void selectCandidate(String candidate){
        page.selectCandidat(candidate);
    }

    @Step
    public void checkCandidateDescription(){
        page.openOfficialCandidatPage();
        page.changeBrowserTab(1);
        String wikiText = page.getWikiText();
        page.changeBrowserTab(0);
        page.checkCandidateDescription(wikiText);
    }

    @Step
    public void checkCandidatesQuantity(){
        page.checkCandidatesQuantity();

    }

    @Step
    public void checkCandidateLink(String candidateName){
        page.openOfficialCandidatPage();
        page.changeBrowserTab(1);
        page.checkCandidateLink(candidateName);

    }

    @Step
    public void checkConfirmVoteElectionPopUp(String candidate){
        page.selectCandidat(candidate);
        page.voiteEllectionBtnClick();
        page.checkVoteElectionPopUp(candidate);
    }

    @Step
    public void confirmVoteElectionPopUp(String candidate){
        page.selectCandidat(candidate);
        page.voiteEllectionBtnClick();
        page.confirmCandidateSelected();

    }
    @Step
    public void checkBallotReceiptPage(){
        page.checkBallotReceiptPage();
    }

    @Step
    public void signBallotReceipt() {
        page.signBallotReceipt();
        page.setValidUniquePin();
        page.checkBallotSignetPageAppears();
    }

    @Step
    public void checkBallotSignedPage(){
        page.checkBallotSignedPage();
    }

    @Step
    public void enterEmail(String email){
        page.setEmail(email);
        page.getHashAndMemo();
    }
}
