package com.example.coursesmarthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button enterButton, newButton;
    EditText loginName, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enterButton = findViewById(R.id.enterButton);
        newButton = findViewById(R.id.newButton);
        loginName = findViewById(R.id.loginName);
        loginPassword = findViewById(R.id.loginPassword);


        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(loginName.getText().toString()) || TextUtils.isEmpty(loginPassword.getText().toString())) {

                    String message = "Заполните поля!";
                    Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();

                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(loginName.getText().toString());
                    loginRequest.setPassword(loginPassword.getText().toString());

                    loginUser(loginRequest);
                }

            }
        });
    }

    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){

                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("data", loginResponse));

                    finish();
                }else{
                    String message = "An error occured please try again";
                    Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }
}