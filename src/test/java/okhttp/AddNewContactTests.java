package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactResponseDTO;;
import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class AddNewContactTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZmF2bmJvbHRAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTEyNjc5NjcsImlhdCI6MTY5MDY2Nzk2N30.FavcVZjsY7qm_gF8NoeI64AfCmUJOHL6hTjuqJXT5_o";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void postAddContactPositive() throws IOException {
        int i = new Random().nextInt(1000) + 1000;

        ContactDTO contactDTO = ContactDTO.builder()
                .name("Nana")
                .lastName("Breg")
                .email("nanabreg" + i + "@mail.com")
                .address("London")
                .phone("442557799000 + i")
                .description("Manager")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
      //  String message = contactResponseDTO.getMessage();
       // System.out.println(message);
//        String id = message.substring(message.lastIndexOf(" ") + 1);
//        System.out.println(id);


    }
}
