package com.example.alex.schoolsengerapplication;

import com.example.alex.schoolsengerapplication.Json.ResponseJson;
import com.example.alex.schoolsengerapplication.Json.UserJson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() throws Exception {
        final HashMap<String, String> userMap = new HashMap<>();

        userMap.put("lastname", "asd");
        userMap.put("firstname", "asd");
        userMap.put("username", "asd");
        userMap.put("character", "asd");

        final UserJson user = new UserJson(userMap);


        final Converter<UserJson> converter1 = new Converter<UserJson>() {

            @Override
            public UserJson fromBody(ResponseBody body) throws IOException {
                Converter<UserJson> converter = (Converter<UserJson>) GsonConverterFactory.create().get(UserJson.class);


                String inputStreamString = new Scanner(body.byteStream(),"UTF-8").next();
                System.out.println(inputStreamString);

                UserJson userJson = converter.fromBody(body);
                return userJson;
            }

            @Override
            public RequestBody toBody(UserJson value) {
                Converter<UserJson> converter = (Converter<UserJson>) GsonConverterFactory.create().get(UserJson.class);
                return converter.toBody(user);
            }
        };

        Converter.Factory factory = new Converter.Factory() {

            @Override
            public Converter<UserJson> get(Type type) {
                return converter1;
            }
        };
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://schoolsenger.herokuapp.com/")
                .addConverterFactory(factory)
                .build();

        Requester requester = retrofit.create(Requester.class);
        Call<ResponseJson> call = requester.sendUserToServer(user);

        call.enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Response<ResponseJson> response) {
                System.out.println("qwe");
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
        Thread.sleep(100000);


//        BufferedSink sink = new Buffer();
//        requestBody.writeTo(sink);
//        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) sink.outputStream();
//        String res = new String(outputStream.toByteArray());
//        System.out.println(res);
    }
}