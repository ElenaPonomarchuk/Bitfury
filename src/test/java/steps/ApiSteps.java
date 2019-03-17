package steps;

import net.thucydides.core.annotations.Step;
import ui.Api;

public class ApiSteps {

    Api api;

    @Step("Get email")
    public void getEmail(){
        api.parceApiResponce();
    }

    @Step("Get email and check email body with expected result")
    public void checkEmail(){
        api.deleteEmail("1");
        api.checkNewEmail();
        api.fetchMail();
    }
}
