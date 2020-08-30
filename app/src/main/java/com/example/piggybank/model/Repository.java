package com.example.piggybank.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.dao.AppDataBase;
import com.example.piggybank.model.dao.MonthlyReportDao;
import com.example.piggybank.model.dao.TransactionDao;
import com.example.piggybank.model.dao.TypeDao;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * calling apis and dbs
 */
public class Repository {

    private MonthlyReportDao monthlyReportDao;
    private TransactionDao transactionDao;
    private TypeDao typeDao;
    private MutableLiveData<List<Transaction>> transactions,transactions2;
    private MutableLiveData<List<Task>> tasks;
    private MutableLiveData<MonthlyReport> monthlyReport;
    private Executor executor;
    private MutableLiveData<Boolean> result;
    Disposable disposable;

    public Repository(Application application) {

        executor = Executors.newFixedThreadPool(2);
        AppDataBase appDataBase = AppDataBase.getInstance(application);
        typeDao = appDataBase.typeDao();
        monthlyReport = new MutableLiveData<>();
        result=new MutableLiveData<>();
    }

    public LiveData<List<Transaction>> getTransactions() {
        return transactionDao.getItems();
    }

    public LiveData<List<MonthlyReport>> getMonthlyReports() {
        return monthlyReportDao.getReport();
    }

    public void insertTransaction(final Transaction transaction) {

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                transactionDao.insert(transaction);
            }
        });
    }

    public MutableLiveData<List<Transaction>> getLastCosts(Single<JsonObject> mApiService, String itemType) {
        transactions=new MutableLiveData<>();

        mApiService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            List<Transaction> tr = new ArrayList<>();
                            JSONObject jso = new JSONObject(jsonObject.toString());
                            String res = jso.getString(itemType);
                            if (res != null) {
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js = jsonArray.getJSONObject(i);
                                    Transaction transaction = new Transaction();
                                    transaction.setAmount(js.getDouble("amount"));
                                    transaction.setId(js.getInt("id"));
                                    transaction.setColor(js.getInt("color"));
                                    transaction.setIdAccount(js.getString("idAccount"));
                                    transaction.setType(js.getInt("type"));
                                    transaction.setItemType(itemType);
                                    transaction.setDates("dates");
                                    tr.add(transaction);


                                }
                                transactions.setValue(tr);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return transactions;
    }
    public MutableLiveData<List<Task>> getTask(Single<JsonObject> mApiService, String itemType) {
        tasks=new MutableLiveData<>();

        mApiService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            List<Task> tr = new ArrayList<>();
                            JSONObject jso = new JSONObject(jsonObject.toString());
                            String res = jso.getString("task");
                            if (res != null) {
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js = jsonArray.getJSONObject(i);
                                    Task task = new Task();
                                    task.setAmountTask(js.getDouble("amount"));
                                    task.setIdTask(js.getInt("id"));
                                    task.setDatesTask(js.getString("dates"));
                                    task.setTitleTask(js.getString("title"));
                                    tr.add(task);


                                }
                                tasks.setValue(tr);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return tasks;
    }
    public MutableLiveData<List<Transaction>>  getLastIncome(Single<JsonObject> mApiService, String itemType) {
        transactions2=new MutableLiveData<>();
        mApiService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            List<Transaction> tr = new ArrayList<>();
                            JSONObject jso = new JSONObject(jsonObject.toString());
                            String res = jso.getString(itemType);
                            if (res != null) {
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js = jsonArray.getJSONObject(i);
                                    Transaction transaction = new Transaction();
                                    transaction.setAmount(js.getDouble("amount"));
                                    transaction.setId(js.getInt("id"));
                                    transaction.setColor(js.getInt("color"));
                                    transaction.setIdAccount(js.getString("idAccount"));
                                    transaction.setType(js.getInt("type"));
                                    transaction.setItemType(itemType);
                                    transaction.setDates("dates");
                                    tr.add(transaction);
                                }
                                transactions2.setValue(tr);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return transactions2;
    }

    public MutableLiveData<MonthlyReport> getReportMonthly(String month) {
        BaseApiService mApiService;
        mApiService = UtilsApi.getAPIService();
        mApiService.getMonthlyReport(month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        Log.d("re", "omad");
                        double totalIncome = 0, totalExpense = 0;
                        MonthlyReport mr = new MonthlyReport();
                        try {
                            JSONObject jsObject = new JSONObject(jsonObject.toString());
                            String res = jsObject.getString("sumCost");
                            String res2 = jsObject.getString("sumIncome");
                            JSONArray jsonArray = new JSONArray(res);
                            JSONArray jsonArray2 = new JSONArray(res2);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject js = jsonArray.getJSONObject(i);
                                if (!js.isNull("SUM(amount)"))
                                    totalExpense = js.getDouble("SUM(amount)");
                                mr.setTotalExpense(totalExpense);
                            }
                            for (int i = 0; i < jsonArray2.length(); i++) {
                                JSONObject js = jsonArray2.getJSONObject(i);
                                if (!js.isNull("SUM(amount)"))
                                    totalIncome = js.getDouble("SUM(amount)");
                                mr.setTotalIncome(totalIncome);
                            }
                            mr.setBalance(totalIncome - totalExpense);
                            mr.setIdAccount("u1");
                            mr.setMonth(month);
                            //result1 = true;
                            monthlyReport.setValue(mr);
                            Log.d("haaa", mr.getMonth());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return monthlyReport;
    }

    public MutableLiveData<List<Transaction>> getItemsMonthly( Single<JsonObject> mApiService) {
        transactions=new MutableLiveData<>();

        mApiService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            List<Transaction> tr = new ArrayList<>();
                            JSONObject jso = new JSONObject(jsonObject.toString());
                            String res = jso.getString("cost");
                            if (res != null) {
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js = jsonArray.getJSONObject(i);
                                    Transaction transaction = new Transaction();
                                    transaction.setAmount(js.getDouble("amount"));
                                    transaction.setId(js.getInt("id"));
                                    transaction.setColor(js.getInt("color"));
                                    transaction.setIdAccount(js.getString("idAccount"));
                                    transaction.setType(js.getInt("type"));
                                    transaction.setItemType("cost");
                                    transaction.setDates("dates");
                                    tr.add(transaction);
                                }
                                transactions.setValue(tr);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return transactions;
    }
    public MutableLiveData<List<Transaction>> getItemsDaily( Single<JsonObject> mApiService) {
        transactions=new MutableLiveData<>();

        mApiService
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            List<Transaction> tr = new ArrayList<>();
                            JSONObject jso = new JSONObject(jsonObject.toString());
                            String res = jso.getString("cost");
                            if (res != null) {
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js = jsonArray.getJSONObject(i);
                                    Transaction transaction = new Transaction();
                                    transaction.setAmount(js.getDouble("amount"));
                                    transaction.setId(js.getInt("id"));
                                    transaction.setColor(js.getInt("color"));
                                    transaction.setIdAccount(js.getString("idAccount"));
                                    transaction.setType(js.getInt("type"));
                                    transaction.setItemType("cost");
                                    transaction.setDates("dates");
                                    tr.add(transaction);
                                }
                                transactions.setValue(tr);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return transactions;
    }
    public MutableLiveData<Boolean> saveItems(Single<JsonObject> mApiService) {
        mApiService.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            JSONObject object = new JSONObject(jsonObject.toString());
                            String res = object.getString("add");
                            Log.d("jsonObj", res);
                            if (res.equals("true")) {
                                Log.d("add", "add");
                                result.setValue(true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        Log.d("result", result+"");
        return result;
    }
    public MutableLiveData<Boolean> deleteItem(Single<JsonObject> mApiService) {
        mApiService.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        try {
                            JSONObject object = new JSONObject(jsonObject.toString());
                            String res = object.getString("delete");
                            Log.d("jsonObj", res);
                            if (res.equals("true")) {
                                Log.d("delete", "delete");
                                result.setValue(true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        Log.d("result", result+"");
        return result;
    }
}



