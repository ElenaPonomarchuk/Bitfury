package steps;

import net.thucydides.core.annotations.Step;

import ui.eVotingPage;

public class eVotingSteps {
    eVotingPage page;


    @Step("Open main URL")
    public void openPresidentialElectionList(){
        page.openURL();
        page.voteElectionBtnClick();
    }

    @Step("Open Candidates of Election Page")
    public void selectEstonianElection(){
        page.selectEstonianPresidential();
        page.voteElectionBtnClick();
    }

    @Step("Select a Candidate")
    public void selectCandidate(String candidate){
        page.selectCandidate(candidate);
    }

    @Step("Select a Candidate and check short Candidate Description")
    public void checkCandidateDescription(){
        page.openOfficialCandidatPage();
        page.changeBrowserTab(1);
        String wikiText = page.getWikiText();
        page.changeBrowserTab(0);
        page.checkCandidateDescription(wikiText);
    }

    @Step("Verify that quantity of candidates = 4")
    public void checkCandidatesQuantity(){
        page.checkCandidatesQuantity();
    }

    @Step("Verify that short description of candidate is correct")
    public void checkCandidateLink(String candidateName){
        page.openOfficialCandidatPage();
        page.changeBrowserTab(1);
        page.checkCandidateLink(candidateName);

    }

    @Step("Verify that all required elements are appear on the Confirm Vole Election Pop up")
    public void checkConfirmVoteElectionPopUp(String candidate){
        page.selectCandidate(candidate);
        page.voteElectionBtnClick();
        page.checkVoteElectionPopUp(candidate);
    }

    @Step("Confirm Vote Election")
    public void confirmVoteElectionPopUp(String candidate){
        page.selectCandidate(candidate);
        page.voteElectionBtnClick();
        page.confirmCandidateSelected();

    }
    @Step("Verify that all required elements are appear on the Ballot Receipt Page")
    public void checkBallotReceiptPage(){
        page.checkBallotReceiptPage();
    }

    @Step("Generate and set a valid pin2")
    public void signBallotReceipt() {
        page.signBallotReceipt();
        page.setValidUniquePin();
        page.checkBallotSignetPageAppears();
    }

    @Step("Verify that all required elements are appear on the Ballot Signed Page")
    public void checkBallotSignedPage(){
        page.checkBallotSignedPage();
    }

    @Step("Get BALLOT RECEIPT 3-WORD MEMO AND HASH after entering email")
    public void enterEmail(String email){
        page.setEmail(email);
        page.getHashAndMemo();
    }
}
