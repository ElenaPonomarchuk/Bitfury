package ui;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Api extends PageObject {


    public void parceApiResponce() {
        String apiUrl = "https://api.guerrillamail.com/ajax.php?f=get_email_address&ip=grr.la/mail/elgie";
        Response response = SerenityRest.when().get(apiUrl);
        response.then().statusCode(200).log().all();
        System.out.println(response);
        JsonPath jsonPathEvaluator = response.jsonPath();
        Serenity.setSessionVariable("email").to(jsonPathEvaluator.get("email_addr"));
        System.out.println(Serenity.sessionVariableCalled("email").toString());
        Serenity.setSessionVariable("token").to(jsonPathEvaluator.get("sid_token"));
        System.out.println(Serenity.sessionVariableCalled("token").toString());

    }
    public void deleteEmail(String emailId){
        Response response = SerenityRest.
                when().
                get("https://api.guerrillamail.com/ajax.php?f=del_email&email_ids[]="+emailId+"&sid_token="+Serenity.sessionVariableCalled("token").toString());
        response.then().statusCode(200).log().all();
    }


    public void checkNewEmail() {
        Response response = SerenityRest.when().
                get("https://api.guerrillamail.com/ajax.php?f=get_email_list&offset=0&sid_token="+Serenity.sessionVariableCalled("token").toString());
        response.then().statusCode(200).log().all();
        JsonPath jsonPathEvaluator = response.jsonPath();
        String id = jsonPathEvaluator.get("list.mail_id[0]");
        System.out.println(id);
        Serenity.setSessionVariable("idMail").to(id);
        System.out.println("Mail ID is: " + Serenity.sessionVariableCalled("idMail").toString());

    }
    public void fetchMail() {
        Response response = SerenityRest.when().
                get("https://api.guerrillamail.com/ajax.php?f=fetch_email&sid_token="+Serenity.sessionVariableCalled("token").toString()+ "&email_id="+Serenity.sessionVariableCalled("idMail").toString());
        response.then().statusCode(200).log().all();
        System.out.println(response);
        // Get mail sender and assert with expected result
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(jsonPathEvaluator.get("mail_from").toString(), containsString("voting2016app@gmail.com"));

        // Get Letter subject and assert with expected result
        assertThat(jsonPathEvaluator.get("mail_subject").toString(), containsString("Voter, your ballot has been successfully posted on public bulletin board"));

        //Get Email Body and assert with expected result
        assertThat(jsonPathEvaluator.get("mail_body").toString(), containsString(Serenity.sessionVariableCalled("hashText").toString()));
        assertThat(jsonPathEvaluator.get("mail_body").toString(), containsString(Serenity.sessionVariableCalled("memoText").toString()));
    }


}
