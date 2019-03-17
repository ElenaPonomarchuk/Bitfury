package steps;

import net.thucydides.core.annotations.Step;
import ui.Api;

public class ApiSteps {
    Api api;

    @Step
    public void getEmail(){
        api.parceApiResponce();

    }

    @Step
    public void checkEmail(){
        api.deleteEmail("1");
        api.checkNewEmail();
        api.fetchMail();
    }
}
