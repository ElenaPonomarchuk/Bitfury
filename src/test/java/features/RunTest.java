package features;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.ApiSteps;
import steps.TestSteps;


@RunWith(SerenityRunner.class)
public class RunTest {
//    private static EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
//    private static String email = variables.getProperty("nameEmail");

    @Managed
    WebDriver driver;

    @Steps
    TestSteps testSteps;

    @Steps
    ApiSteps apiSteps;

//    @Test
    public void checkCandidatesQuantity(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.checkCandidatesQuantity();

    }

//    @Test
    public void checkCanditateLink(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.selectCandidate("Eiki Nestor");
        testSteps.checkCandidateLink("Eiki Nestor");

    }

//    @Test
    public void checkCandidateDescription(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.selectCandidate("Eiki Nestor");
        testSteps.checkCandidateDescription();
    }

//    @Test
    public void confirmVoteElectionPopUp(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.checkConfirmVoteElectionPopUp("Eiki Nestor");
    }

//    @Test
    public void checkBallotReceiptPage(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.checkBallotReceiptPage();

    }

//    @Test
    public void setValidPin2(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.signBallotReceipt();
    }

//    @Test
    public void checkBallotSignedPage(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.signBallotReceipt();
        testSteps.checkBallotSignedPage();
    }

    @Test
    public void sentMail(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.confirmVoteElectionPopUp("Eiki Nestor");
        testSteps.signBallotReceipt();
        testSteps.checkBallotSignedPage();
        apiSteps.getEmail();
        testSteps.enterEmail(Serenity.sessionVariableCalled("email").toString());
        apiSteps.checkEmail();
    }

//    @Test
    public void api(){
        apiSteps.getEmail();
    }


}
