package org.example.tests;

import okhttp3.OkHttpClient;
import org.example.api.UserAPIClient;
import org.example.builder.HeadersBuilder;
import org.example.builder.OkhttpClientBuilder;
import org.example.builder.RetrofitBuilder;
import org.example.factory.RetrofitClientFactory;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Retrofit;

public class UserAPITests {

    protected UserAPIClient userAPIClient;

    @BeforeEach
    public void setUp() {
        OkHttpClient okHttpClient =
                OkhttpClientBuilder.builder().addHeaders(
                        HeadersBuilder.builder()
                                .setHeaders("Content-Type", "application/json")
                                .build()
                ).build();
        Retrofit retrofit =
                RetrofitBuilder.builder()
                        .setBaseUrl("https://reqres.in/")
                        .setCallAdapterFactory("gson")
                        .client(okHttpClient)
                        .build();
        this.userAPIClient =
                RetrofitClientFactory.createApiClient(retrofit, UserAPIClient.class);
    }
}
