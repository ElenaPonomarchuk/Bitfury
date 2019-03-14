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
    public void selectCandidate(){
        page.selectCandidat();
        page.getDescription();
    }

}
