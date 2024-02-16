package org.example.tests;

import com.github.javafaker.Faker;
import org.example.factory.RetrofitClientFactory;
import org.example.models.request.User;
import org.example.models.response.UserCreated;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPostTests extends UserAPITests{
    @Test
    public void testCreateUser() {
        Faker faker = new Faker();
        final String name = faker.name().fullName();
        final String job = faker.commerce().department();
        Response<UserCreated> userCreatedResponse =
                RetrofitClientFactory.executeCall(
                        userAPIClient.createUser(User.builder().name(name).job(job).build())
                );
        assert userCreatedResponse.body() != null;
        System.out.println(userCreatedResponse.body());
        assertEquals(userCreatedResponse.code(), 201, "Status Code");
        assertEquals(userCreatedResponse.body().getName(), name, "Full Name");
        assertEquals(userCreatedResponse.body().getJob(), job, "Job");
    }
}
