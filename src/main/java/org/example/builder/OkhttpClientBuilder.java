package org.example.builder;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpClientBuilder {

    private OkhttpClientBuilder() {
    }

    private static OkHttpClient.Builder okHttpBuilder;


    public static OkhttpClientBuilder builder() {
        okHttpBuilder = new OkHttpClient.Builder();
        return new OkhttpClientBuilder();
    }


    public OkhttpClientBuilder addBasicAuthentication(final String userName, final String password) {
        okHttpBuilder.
                authenticator(
                        (route, response) -> response.request().newBuilder().addHeader("Authorization", Credentials.basic(userName, password)).build());
        return this;
    }

    public OkhttpClientBuilder addToken(final String token) {
        okHttpBuilder.
                authenticator(
                        (route, response) -> response.request().newBuilder().addHeader("Authorization", token).build());
        return this;
    }

    public OkhttpClientBuilder addHeaders(final Headers headers) {
        okHttpBuilder.
                authenticator(
                        (route, response) -> response.request().newBuilder().headers(headers).build());
        return this;
    }

    public OkhttpClientBuilder addBasicAuthenticationUsingInterceptor(final String key, final String value) {
        okHttpBuilder.addInterceptor(chain -> {
            Request build = chain.request().newBuilder().addHeader(key, value).build();
            return chain.proceed(build);
        });
        return this;
    }

    public OkHttpClient build() {
        return okHttpBuilder.build();
    }
}
