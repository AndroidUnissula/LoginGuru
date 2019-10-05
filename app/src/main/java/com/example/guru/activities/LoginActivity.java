package com.example.guru.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guru.R;
import com.example.guru.model.LoginResponse;
import com.example.guru.networking.ServiceClient;
import com.example.guru.networking.ServiseGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtKodeGuru, edtPass;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtKodeGuru = findViewById(R.id.edt_kode_guru);
        edtPass = findViewById(R.id.edt_pass);

        pd = new ProgressDialog(this);
    }

    public void btnLogin(View view) {
        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        if(edtKodeGuru.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this, "Kode guru tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        if(edtPass.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }

        String kodeGuru = edtKodeGuru.getText().toString().trim().toUpperCase();
        String passGuru = edtPass.getText().toString().trim();

        ServiceClient service = ServiseGenerator.createService(ServiceClient.class);


        Call<LoginResponse> requestLogin = service.loginGuru("loginGuru", "login",kodeGuru, passGuru);

        requestLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if(response.body().getHasil().equals("success")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "login gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // server tidak memberikan respon / terputus
                Toast.makeText(LoginActivity.this, "Koneksi error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
