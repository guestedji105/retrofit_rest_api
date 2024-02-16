package org.example.tests;

import org.example.factory.RetrofitClientFactory;
import org.example.models.response.Pages;
import org.example.models.response.SingleUserResponse;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetTests extends UserAPITests {

    @Test
    public void testUserDetailsForSpecificID() {
        Response<SingleUserResponse> singleUserResponseResponse =
                RetrofitClientFactory.executeCall(userAPIClient.getUsersById("2"));
        assertEquals(singleUserResponseResponse.code(), 200, "Status Code");
        assert singleUserResponseResponse.body() != null;
        assertEquals(
                singleUserResponseResponse.body().getData().getId(),
                2, "User ID");
        assertEquals(
                singleUserResponseResponse.body().getData().getEmail(),
                "janet.weaver@reqres.in", "Email");
        assertEquals(
                singleUserResponseResponse.body().getData().getFirst_name(),
                "Janet", "First Name");
        assertEquals(
                singleUserResponseResponse.body().getData().getLast_name(),
                "Weaver", "Last Name");
        assertEquals(
                singleUserResponseResponse.body().getData().getAvatar(),
                "https://reqres.in/img/faces/2-image.jpg", "Avatar");
    }

    @Test
    public void testUserDetailsForPagination() {
        Response<Pages> recordsResponse =
                RetrofitClientFactory.executeCall(
                        userAPIClient.getUserDetailsWithPagination(2)
                );
        assertEquals(recordsResponse.code(), 200, "Status Code");
        assert recordsResponse.body() != null;
        assertEquals(recordsResponse.body().getData().length, 6, "Data Count");
    }
}
