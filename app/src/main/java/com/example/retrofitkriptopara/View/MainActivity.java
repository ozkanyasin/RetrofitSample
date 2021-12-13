package com.example.retrofitkriptopara.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.retrofitkriptopara.Adapter.RecyclerAdapter;
import com.example.retrofitkriptopara.Model.CryptoModel;
import com.example.retrofitkriptopara.Service.CryptoAPI;
import com.example.retrofitkriptopara.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerAdapter recyclerAdapter;

    CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()       // retrofit nesnemizi oluşturduk
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // rx ile çalışmak için retrofit nesne oluşturulurken bunu ekliyoruz
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }

    private void loadData(){

        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable = new CompositeDisposable();        // burası rxjava ile bağlama
        compositeDisposable.add(cryptoAPI.geData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));

      /*  Call<List<CryptoModel>> call = cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    //recycler işlemleri
                    activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerAdapter = new RecyclerAdapter(cryptoModels);
                    activityMainBinding.recyclerView.setAdapter(recyclerAdapter);

                    *//*for (CryptoModel cryptoModel : cryptoModels){
                        System.out.println(cryptoModel.currency);
                    }*//*

                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/

    }


    private void handleResponse(List<CryptoModel> cryptoModelList){

        cryptoModels = new ArrayList<>(cryptoModelList);

        //recycler işlemleri
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerAdapter = new RecyclerAdapter(cryptoModels);
        activityMainBinding.recyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}