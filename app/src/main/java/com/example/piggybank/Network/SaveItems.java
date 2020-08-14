package com.example.piggybank.Network;

import android.util.Log;
import android.view.View;
import com.example.piggybank.ui.Progress;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SaveItems {
    static boolean result = false;
    private onSaveItem mlistener;

    public interface onSaveItem {
        void onItemClick(boolean result);
    }

    public void saveOnItemListener(SaveItems.onSaveItem listener) {
        mlistener = listener;
    }

    public static void saveCost(double amount, int color, String type, String idAccount, String dates, final Progress progress, final onSaveItem mlistener) {
        BaseApiService mApiService;
        progress.setVisibility(View.VISIBLE);
        mApiService = UtilsApi.getAPIService();
        mApiService.saveCost(amount, color, type, idAccount, dates).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        progress.setVisibility(View.GONE);
                        try {
                            JSONObject object = new JSONObject(jsonObject.toString());
                            String res = object.getString("add");
                            Log.d("jsonObj", res.toString());
                            if (res.equals("true")) {
                                result = true;

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        mlistener.onItemClick(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void saveIncome(double amount, int color, String type, String idAccount, String dates, final Progress progress, final onSaveItem mlistener) {
        BaseApiService mApiService;
        progress.setVisibility(View.VISIBLE);
        mApiService = UtilsApi.getAPIService();
        mApiService.saveIncome(amount, color, type, idAccount, dates).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        progress.setVisibility(View.GONE);
                        try {
                            JSONObject object = new JSONObject(jsonObject.toString());
                            String res = object.getString("add");
                            Log.d("jsonObj", res.toString());
                            if (res.equals("true")) {
                                result = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        mlistener.onItemClick(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
