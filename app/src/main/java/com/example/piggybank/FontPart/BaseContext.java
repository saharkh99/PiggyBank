package com.example.piggybank.FontPart;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BaseContext extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}