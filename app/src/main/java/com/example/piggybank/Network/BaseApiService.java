package com.example.piggybank.Network;

import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
//changed
public interface BaseApiService {

    @FormUrlEncoded
    @POST("wallet/addCost.php")
    Single<JsonObject> saveCost(@Field("amount") double amount,
                                @Field("color") int color,
                                @Field("type") int type,
                                @Field("idAccount") String idAccount,
                                @Field("dates") String dates

    );

    @FormUrlEncoded
    @POST("wallet/addIncome.php")
    Single<JsonObject> saveIncome(@Field("amount") double amount,
                                  @Field("color") int color,
                                  @Field("type") int type,
                                  @Field("idAccount") String idAccount,
                                  @Field("dates") String dates


    );

    @FormUrlEncoded
    @POST("wallet/getCosts.php")
    Single<JsonObject> getLastCosts(@Field("limit") int limit);

    @FormUrlEncoded
    @POST("wallet/getIncomes.php")
    Single<JsonObject> getLastIncomes(@Field("limit") int limit);

    @FormUrlEncoded
    @POST("wallet/monthlyReport.php")
    Single<JsonObject> getMonthlyReport(
            @Field("month") String month
    );
    @FormUrlEncoded
    @POST("wallet/getCostsMonthly.php")
    Single<JsonObject> getItemsMonthly(
            @Field("month") String month
    );
    @FormUrlEncoded
    @POST("wallet/getCostsDaily.php")
    Single<JsonObject> getItemsDaily(
            @Field("dates") String date
    );

}
