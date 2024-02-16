package org.example.api;

import org.example.models.request.User;
import org.example.models.response.Pages;
import org.example.models.response.SingleUserResponse;
import org.example.models.response.UserCreated;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserAPIClient {

    @GET("api/users/{id}")
    Call<SingleUserResponse> getUsersById(@Path("id") String id);

    @POST("api/users")
    Call<UserCreated> createUser(@Body User user);

    @GET("api/users")
    Call<Pages> getUserDetailsWithPagination(@Query("page") int pageRecords);

}

