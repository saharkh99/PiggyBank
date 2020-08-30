package com.example.piggybank.Network;

import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * its too much just because i wasn't sure about my php code :)
 */
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
    @POST("wallet/addTask.php")
    Single<JsonObject> saveTask(@Field("amount") double amount,
                                  @Field("title") String title,
                                  @Field("date") String date

    );
    @FormUrlEncoded
    @POST("wallet/deleteRecord.php")
    Single<JsonObject> deleteItem(@Field("table") String table,@Field("id")int id);

    @FormUrlEncoded
    @POST("wallet/getCosts.php")
    Single<JsonObject> getLastCosts(@Field("limit") int limit);

    @FormUrlEncoded
    @POST("wallet/getIncomes.php")
    Single<JsonObject> getLastIncomes(@Field("limit") int limit);
    @FormUrlEncoded
    @POST("wallet/getTask.php")
    Single<JsonObject> getTask(@Field("limit") int limit);

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
