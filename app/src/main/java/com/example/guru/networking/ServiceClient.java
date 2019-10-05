package com.example.guru.networking;

import com.example.guru.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceClient {

    @POST("exec")
    @FormUrlEncoded
    Call<LoginResponse> loginGuru(@Field(value = "tabel",encoded = true) String tabel,
                                  @Field(value = "fungsi",encoded = true) String fungsi,
                                  @Field(value = "kodeGuru",encoded = true) String kodeGuru,
                                  @Field(value = "pass",encoded = true) String pass);
}
