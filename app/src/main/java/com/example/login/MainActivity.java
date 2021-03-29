package com.example.login;


import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.login.ModelResponse.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText etuserName;
    EditText etpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuserName=findViewById(R.id.etuserName);
        etpassword=findViewById(R.id.etpassword);
        b1 = findViewById(R.id.btnlogin);
        b1.setOnClickListener(v -> loginUser());

    }
    private void loginUser() {
        String userName = etuserName.getText().toString() ;
        String password = etpassword.getText().toString();
        if(userName.isEmpty())
        {
            etuserName.requestFocus();
            etuserName.setError("Please Enter Username");
            return;

        }
        if(password.isEmpty())
        {
            etpassword.requestFocus();
            etpassword.setError("Please Enter Password");
            return;

        }
        HashMap<String,String> data= new HashMap<>();
        HashMap<String, String> d = new HashMap<>();
        data.put("userName", userName);
        data.put("password", password);

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .Login(data) ;
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {
                    assert loginResponse != null;
                    Toast.makeText(MainActivity.this, loginResponse.getToken(), Toast.LENGTH_SHORT).show();
                } else {
                    /*switch(response.code()){
                        case 400:
                            Toast.makeText(MainActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(MainActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                    }*/
                    ApiError apiError=ErrorsUtils.parseError(response);
                    Toast.makeText(MainActivity.this,apiError.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Api Link Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
