
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

import java.util.List;


public class GetPetFindByStatusAvailable {

    public static Response doGetRequest(String endpoint) {
        defaultParser = Parser.JSON;
        return
                given().
                        queryParam("status", "available").
                        when().
                        get(endpoint).
                        then().contentType(ContentType.JSON).
                        extract().response();
    }
    public static void main(String[] args) {
        Response response = doGetRequest("https://petstore.swagger.io/v2/pet/findByStatus");
        List<String> jesonResponse = response.jsonPath().getList("$");
        System.out.println(jesonResponse);
    }


    @Test
    public void GetAvailablePetTest() {
        given().
                queryParam("status", "available").
        expect().
                statusCode(200).
                body(
                        "name", equalTo("ASYDog"),
                        "id", equalTo(38316084),
                        "category.name", equalTo("borko")
                        );
        when().
                get("https://petstore.swagger.io/v2/pet/findByStatus");
    }


}
