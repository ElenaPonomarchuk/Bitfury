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

    @Test
    public void firstTest(){
        testSteps.openPresidantialElectionList();
        testSteps.selectEstonianElection();

    }

}
