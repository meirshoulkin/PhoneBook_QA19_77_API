package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactsTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDE3OSwiaWF0IjoxNjkwMjIwMTc5fQ.KFz1TZTbB0KHlzVmNO1j38brzfu3Ve9bCyOc-cjimJ8";

    String id;
    ContactDTO contactDTO;

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";

        int i = new Random().nextInt(1000) + 1000;

         contactDTO = ContactDTO.builder()
                .name("QA19")
                .lastName("Automation")
                .email("qa19" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Haifa")
                .description("Students")
                .build();

        given()
                .header("Authorization", token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("message");
    }

    }


