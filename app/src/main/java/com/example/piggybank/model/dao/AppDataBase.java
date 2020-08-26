package com.example.piggybank.model.dao;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.piggybank.R;
import com.example.piggybank.model.Account;
import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;
import com.example.piggybank.model.Type;

//changed
@Database(entities = {Transaction.class,MonthlyReport.class, Type.class, Account.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase INSTANCE;

    public abstract TypeDao typeDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(INSTANCE).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private TypeDao typeDao;

        public InitialDataAsyncTask(AppDataBase appDataBase) {

            typeDao = appDataBase.typeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Type type=new Type();
            type.setNameType("پزشکی");
            type.setIdType(1);
            type.setIconType(R.drawable.ambulance);
            typeDao.insert(type);
            type.setNameType("آرایشی بهداشتی");
            type.setIdType(2);
            type.setIconType(R.drawable.barbershop);
            typeDao.insert(type);
            type.setNameType("قبض");
            type.setIdType(3);
            type.setIconType(R.drawable.bill);
            typeDao.insert(type);
            type.setNameType("کارت به کارت");
            type.setIdType(4);
            type.setIconType(R.drawable.cardexchange);
            typeDao.insert(type);
            type.setNameType("لباس");
            type.setIdType(5);
            type.setIconType(R.drawable.clothes);
            typeDao.insert(type);
            type.setNameType("غذا");
            type.setIdType(6);
            type.setIconType(R.drawable.food);
            typeDao.insert(type);
            type.setNameType("بنزین");
            type.setIdType(7);
            type.setIconType(R.drawable.gasstation);
            typeDao.insert(type);
            type.setNameType("هدیه");
            type.setIdType(8);
            type.setIconType(R.drawable.gift);
            typeDao.insert(type);
            type.setNameType("حقوق");
            type.setIdType(9);
            type.setIconType(R.drawable.income);
            typeDao.insert(type);
            type.setNameType("اینترنت");
            type.setIdType(10);
            type.setIconType(R.drawable.internet);
            typeDao.insert(type);
            type.setNameType("حمل و نقل");
            type.setIdType(11);
            type.setIconType(R.drawable.transport);
            typeDao.insert(type);
            type.setNameType("بانکی");
            type.setIdType(12);
            type.setIconType(R.drawable.check);
            typeDao.insert(type);
            return null;
        }
    }

}