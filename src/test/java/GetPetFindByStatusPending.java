
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.util.List;


public class GetPetFindByStatusPending {
    // parsing JSON method to check the fields
    public static Response doGetRequest (String endpoint) {
        defaultParser = Parser.JSON;
        return
                given().
                        queryParam("status", "pending").
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
    public void findingPetByStatusPendingTest() {
        given().
                queryParam("status", "pending").
        expect().
                statusCode(200).
                body(
                        "name[1]", equalTo("doggie"),
                        "id[1]", equalTo(-79),
                        "category.name[1]", equalTo("znmMeP9tpx5UB4RA")
                        ).
                when().
                    get("https://petstore.swagger.io/v2/pet/findByStatus");
    }

}
