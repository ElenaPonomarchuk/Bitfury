package ui;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import java.util.Map;

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
//        SerenityRest.when().get("https://api.guerrillamail.com/ajax.php?f=get_email_address&ip=grr.la/mail/elgie");
        waitABit(30000);
        String apiUrl = "https://api.guerrillamail.com/ajax.php?f=get_email_list&offset=0&sid_token="+Serenity.sessionVariableCalled("token").toString();
        System.out.println("Api url = "+apiUrl);
        Response response = SerenityRest.when().get(apiUrl);
        response.then().statusCode(200).log().all();
        System.out.println(response);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String id = jsonPathEvaluator.get("list.mail_id").toString().replaceAll("\\[","").replaceAll("\\]","");
        System.out.println("Mail: "+ id);


    }

}
