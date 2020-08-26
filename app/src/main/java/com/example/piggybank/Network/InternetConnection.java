package com.example.piggybank.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetConnection extends BroadcastReceiver {
    View v;
    Snackbar snackbar;

    public InternetConnection(View view) {
        v = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (isConnected) {
            if (snackbar != null)
                snackbar.dismiss();
        } else {
            snackbar = Snackbar.make(v, "به ایرنت وصل شوید", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }

    }
}
