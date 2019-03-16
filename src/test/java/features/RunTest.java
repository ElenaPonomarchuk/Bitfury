package features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.TestSteps;


@RunWith(SerenityRunner.class)
public class RunTest {

    @Managed
    WebDriver driver;

    @Steps
    TestSteps testSteps;

//    @Test
    public void checkCandidatesQuantity(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.checkCandidatesQuantity();

    }

    @Test
    public void checkCanditateLink(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.selectCandidate("Eiki Nestor");
        testSteps.checkCandidateLink("Eiki Nestor");

    }

//    @Test
    public void e2eTest(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();
        testSteps.selectCandidate("Eiki Nestor");
        testSteps.checkCandidateDescription();
    }

}
