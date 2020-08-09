package com.example.piggybank.Network;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("wallet/addCost.php")
    Call<JsonObject> saveCost(@Field("amount") double amount,
                              @Field("color") int color,
                              @Field("type") String type,
                              @Field("idAccount") String idAccount,
                              @Field("dates")String dates

    );
    @FormUrlEncoded
    @POST("wallet/addIncome.php")
    Call<JsonObject> saveIncome(@Field("amount") double amount,
                                @Field("color") int color,
                                @Field("type") String type,
                                @Field("idAccount") String idAccount,
                                @Field("dates")String dates


    );

    @GET("wallet/lastCost.php")
    Call<JsonObject> getLastCosts();

    @GET("wallet/lastIncomes.php")
    Call<JsonObject> getLastIncomes();

    @FormUrlEncoded
    @POST("wallet/monthlyReport.php")
    Call<JsonObject>getMonthlyReport(
            @Field("month") String month
            );

}