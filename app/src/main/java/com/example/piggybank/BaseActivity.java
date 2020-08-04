package com.example.piggybank;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.example.piggybank.adapter.ViewPagerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class BaseActivity extends AppCompatActivity {

    private boolean isMenuOpen=false;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ImageView img;
    private FloatingActionButton floatCost,floatAdd,floatIncome;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RtlViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.report
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        findView();
        setToolBar();
        addItem();
        addCost();
        addIncome();
        setupViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();
            }
        });
    }

    private void addIncome() {
        floatIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewItemFragment bottomFragment = new NewItemFragment(false);
                bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());            }
        });
    }

    private void addCost() {
        floatCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewItemFragment bottomFragment = new NewItemFragment(true);
                bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());            }
        });
    }

    private void addItem() {
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isMenuOpen){
                    showMenu();
                }else{
                    closeMenu();
                }
            }
        });
    }

    private void closeMenu() {
        isMenuOpen=false;
        floatCost.animate().translationY(0);
        floatIncome.animate().translationY(0);
    }

    private void showMenu() {
        isMenuOpen=true;
        floatCost.animate().translationY(-getResources().getDimension(R.dimen.move_60));
        floatIncome.animate().translationY(-getResources().getDimension(R.dimen.move_115));

    }

    private void findView() {
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer);
        img=findViewById(R.id.imgview);
        tabLayout=findViewById(R.id.pager_header);
        viewPager=findViewById(R.id.viewpager);
        floatAdd=findViewById(R.id.add_item);
        floatCost=findViewById(R.id.add_cost);
        floatIncome=findViewById(R.id.add_money);
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
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars,0,0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setNavigationIcon(R.drawable.menu);
    }


}