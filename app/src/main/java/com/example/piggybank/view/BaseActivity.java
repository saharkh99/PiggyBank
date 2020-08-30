package com.example.piggybank.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.example.piggybank.Network.InternetConnection;
import com.example.piggybank.R;
import com.example.piggybank.Util.NotificationService;
import com.example.piggybank.Util.Types;
import com.example.piggybank.adapter.ViewPagerAdapter;
import com.example.piggybank.viewmodel.BaseViewModel;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class BaseActivity extends AppCompatActivity {
    private boolean isMenuOpen = false;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ImageView img;
    private FloatingActionButton floatCost, floatAdd, floatIncome;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RtlViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView calendarImg;
    private InternetConnection internetConnection;
    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.report
    };
    private BaseViewModel baseViewModel;
    private BaseObservable binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        baseViewModel = ViewModelProviders.of(this,
                new FragmentFactory(this.getApplication()))
                .get(BaseViewModel.class);
        findView();
        setToolBar();
        addItem();
        addCost();
        addIncome();
        setCalender();
        setupViewPager(viewPager);
        setAlarms();
        tabLayout.post(() -> {
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons();
        });
    }

    private void setCalender() {
        calendarImg.setOnClickListener(view -> {
            Calendar calendar = new Calendar();
            calendar.show(getSupportFragmentManager(), calendar.getTag());
        });
    }

    private void addIncome() {
        floatIncome.setOnClickListener(view -> {
            final NewItemFragment bottomFragment = new NewItemFragment(false);
            bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());
        });
    }

    private void addCost() {
        floatCost.setOnClickListener(view -> {
            NewItemFragment bottomFragment = new NewItemFragment(true);
            bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());
        });
    }

    private void addItem() {
        floatAdd.setOnClickListener(view -> {
            if (!isMenuOpen) {
                showMenu();
            } else {
                closeMenu();
            }
        });
    }

    private void closeMenu() {
        isMenuOpen = false;
        floatCost.animate().translationY(0);
        floatIncome.animate().translationY(0);
    }

    private void showMenu() {
        isMenuOpen = true;
        floatCost.animate().translationY(-getResources().getDimension(R.dimen.move_60));
        floatIncome.animate().translationY(-getResources().getDimension(R.dimen.move_115));

    }

    private void findView() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        img = findViewById(R.id.imgview);
        tabLayout = findViewById(R.id.pager_header);
        viewPager = findViewById(R.id.viewpager);
        floatAdd = findViewById(R.id.add_item);
        floatCost = findViewById(R.id.add_cost);
        floatIncome = findViewById(R.id.add_money);
        calendarImg = findViewById(R.id.calender);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(RtlViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "خانه");
        adapter.addFragment(new ReportFragment(), "گزارش");
        viewPager.setAdapter(adapter);
    }

    public void setToolBar() {
        Toolbar toolbars = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        setSupportActionBar(toolbars);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setNavigationIcon(R.drawable.menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        internetConnection = new InternetConnection(drawerLayout);
        registerReceiver(internetConnection, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(internetConnection);
    }

    private AlarmManager getAlarmManager() {
        return (AlarmManager) this.getSystemService(ALARM_SERVICE);
    }

    private void createAlarm(Intent i, int requestCode, long timeInMillis) {
        AlarmManager am = getAlarmManager();
        PendingIntent pi = PendingIntent.getService(this.getApplicationContext(), requestCode, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, timeInMillis, pi);
    }

    private void setAlarms() {

        baseViewModel.getTask().observe(this, tasks -> {

            for (int index = 0; index < tasks.size(); index++) {
                if (tasks.get(index).getDatesTask().equals(Types.getDate(true))) {
                    Log.d("tasks", tasks.get(index).getDatesTask() + Types.getDate(true));
                    Intent i = new Intent(getApplicationContext(), NotificationService.class);
                    i.putExtra(NotificationService.TODOUUID, "یاداوری های امروز");
                    i.putExtra(NotificationService.TODOTEXT, tasks.get(index).getAmountTask() + "فراموش نشود" + tasks.get(index).getTitleTask());
                    createAlarm(i, 100, 1000);
                }
            }
        });
    }
}