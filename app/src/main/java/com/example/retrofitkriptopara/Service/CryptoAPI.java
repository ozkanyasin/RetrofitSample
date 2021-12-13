package com.example.retrofitkriptopara.Service;

import com.example.retrofitkriptopara.Model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //URL BASE -> www.website.com
    //https://api.nomics.com/v1/prices?key=6650dba29ebf39a9794d50aebf1ed80ad4af8edc

    @GET("prices?key=6650dba29ebf39a9794d50aebf1ed80ad4af8edc") //bu adrese bir istek gönder

    Observable<List<CryptoModel>> geData();     //RxJava ile

   // Call<List<CryptoModel>> getData();  ** retrofit ile yapmak için

}
