package com.example.coursesmarthouse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("user")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);


    @POST("user")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

}
