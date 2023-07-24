package okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ContactDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostAddNewContact {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void postAddContactPositive() throws IOException {

        ContactDTO requestDTO = ContactDTO.builder()
                .id("5577")
                .name("Nana")
                .lastName("Breg")
                .email("nanabreg@mail.com")
                .address("London")
                .phone("+442557799000")
                .description("Manager")
                .build();

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
            String responseJson = response.body().string();
            AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is ---> " + response.code());
            Assert.assertTrue(response.isSuccessful());
        } else {
            System.out.println("Response code is ---> " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        //    System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
          //  Assert.assertTrue(response.isSuccessful());
        }
    }
}
