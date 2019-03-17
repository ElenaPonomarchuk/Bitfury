package features;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.ApiSteps;
import steps.eVotingSteps;


@RunWith(SerenityRunner.class)
public class eVotingTest {

    @Managed
    WebDriver driver;

    @Steps
    eVotingSteps testSteps;

    @Steps
    ApiSteps apiSteps;

    @Test
    public void checkCandidatesQuantity(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.checkCandidatesQuantity();
    }

    @Test
    public void checkCanditateLink(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.selectCandidate("Eiki Nestor");
        testSteps.checkCandidateLink("Eiki Nestor");
    }

    @Test
    public void checkCandidateDescription(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.selectCandidate("Eiki Nestor");
        testSteps.checkCandidateDescription();
    }

    @Test
    public void confirmVoteElectionPopUp(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.checkConfirmVoteElectionPopUp("Eiki Nestor");
    }

    @Test
    public void checkBallotReceiptPage(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.checkBallotReceiptPage();
    }

    @Test
    public void setValidPin2(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.signBallotReceipt();
    }

    @Test
    public void checkBallotSignedPage(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.signBallotReceipt();
        testSteps.checkBallotSignedPage();
    }

    @Test
    public void checkDeliveredEmail(){
        testSteps.openPresidentialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.signBallotReceipt();
        testSteps.checkBallotSignedPage();
        apiSteps.getEmail();
        testSteps.enterEmail(Serenity.sessionVariableCalled("email").toString());
        apiSteps.checkEmail();
    }
}
