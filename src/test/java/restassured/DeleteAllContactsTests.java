package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteAllContactsTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDE3OSwiaWF0IjoxNjkwMjIwMTc5fQ.KFz1TZTbB0KHlzVmNO1j38brzfu3Ve9bCyOc-cjimJ8";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void deleteAllContactsPositive(){

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .delete("contacts/clear")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contacts was deleted!"));

    }
}
