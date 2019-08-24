package com.aottec.arkotgps.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aottec.arkotgps.Fragment.AlarmFragment;
import com.aottec.arkotgps.Fragment.SettingsFragment;
import com.aottec.arkotgps.Model.DrawerObjectResponseModel;
import com.aottec.arkotgps.NavigationAdaptor;
import com.aottec.arkotgps.Util.AppConstants;
import com.aottec.arkotgps.Util.GlobalValues;
import com.aottec.arkotgps.Fragment.TrackingFragment;
import com.aottec.arkotgps.R;
import com.aottec.arkotgps.Util.APIClient;
import com.aottec.arkotgps.Util.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FrameLayout content_frame;
    private ImageView toolbar;
    DrawerLayout drawer;
    private RecyclerView recyclerDrawerObject;
    NavigationAdaptor navigationAdaptor;
    ImageView ivRefresh;
    private EditText searchField;
    private ArrayList<DrawerObjectResponseModel> data = new ArrayList<>();
    GlobalValues globalValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        initializeNavigationView();
        globalValues=new GlobalValues(this);
        setOnClickListener();
        getDrawerObject();

    }

    private void setOnClickListener() {

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);

                } else {
                    drawer.openDrawer(Gravity.LEFT);

                }
            }
        });
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerDrawerObject.setVisibility(View.GONE);
                getDrawerObject();

            }
        });


    }

    private void getDrawerObject() {
        ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);
        String xapi = globalValues.getString("api_key");


        Map<String, String> mapdata = new HashMap<>();
        mapdata.put("key", xapi);
        mapdata.put("ver", "3.9");
        Call<ArrayList<DrawerObjectResponseModel>> call = apiService.calluserObject(mapdata);
        call.enqueue(new Callback<ArrayList<DrawerObjectResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DrawerObjectResponseModel>> call, Response<ArrayList<DrawerObjectResponseModel>> response) {

                String s = String.valueOf(response);

                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        recyclerDrawerObject.setVisibility(View.VISIBLE);
                        data = response.body();
                        navigationAdaptor = new NavigationAdaptor(MainActivity.this,data );
                        recyclerDrawerObject.setHasFixedSize(true);
                        recyclerDrawerObject.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerDrawerObject.setAdapter(navigationAdaptor);
                        navigationAdaptor.notifyDataSetChanged();
                        navigationAdaptor.setAddClickListener(new NavigationAdaptor.AddClickEvent() {
                            @Override
                            public void onListClick(int position) {
                                AppConstants.selectedPosition = position;
                                navigationAdaptor.notifyDataSetChanged();
                                globalValues.put("storedLat",data.get(position).getLat());
                                globalValues.put("storedLong",data.get(position).getLng());
                                drawer.closeDrawer(Gravity.LEFT);

                                toolbar.setVisibility(View.VISIBLE);
                                TrackingFragment trackingFragment = new TrackingFragment();



                                android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                                fragmentTransaction1.replace(R.id.content_frame, trackingFragment);
                                fragmentTransaction1.commit();
                            }
                        });

                        searchField.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                                // filter your list from your input
                                filter(s.toString());
                                //you can use runnable postDelayed like 500 ms to delay search text
                            }
                        });

                    } else {
                        recyclerDrawerObject.setVisibility(View.GONE);
                    }


                }


            }

            @Override
            public void onFailure(Call<ArrayList<DrawerObjectResponseModel>> call, Throwable t) {
                //  progressBar.setVisibility(View.GONE);
                String error = String.valueOf(call);
            }


        });
    }

    void filter(String text){
        ArrayList<DrawerObjectResponseModel> temp = new ArrayList();
        for(DrawerObjectResponseModel d: data){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().toUpperCase().contains(text.toUpperCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        navigationAdaptor.updateList(temp);
    }


    private void initializeView() {
        ivRefresh = findViewById(R.id.ic_refresh);
        drawer = findViewById(R.id.drawer_data);
        recyclerDrawerObject = findViewById(R.id.nav_list);
        searchField = findViewById(R.id.searchField);
        toolbar = findViewById(R.id.toolbar);
        content_frame = findViewById(R.id.content_frame);

    }

    private void initializeNavigationView() {
        BottomNavigationView bottomNavigation = findViewById(R.id.navigation);
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_track:

                        unlockDrawer();
                        toolbar.setVisibility(View.VISIBLE);
                        TrackingFragment trackingFragment = new TrackingFragment();

                        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, trackingFragment);
                        fragmentTransaction.commit();


                        return true;
                    case R.id.action_alarm:

                        lockDrawer();
                        toolbar.setVisibility(View.GONE);
                        AlarmFragment alarmFragment = new AlarmFragment();

                        android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                        fragmentTransaction1.replace(R.id.content_frame, alarmFragment);
                        fragmentTransaction1.commit();


                        return true;
                    case R.id.action_setting:

                        lockDrawer();
                        toolbar.setVisibility(View.GONE);
                        SettingsFragment settingsFragment = new SettingsFragment();

                        android.support.v4.app.FragmentManager fragmentManager2 = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.content_frame, settingsFragment);
                        fragmentTransaction2.commit();


                        return true;

                }

                return false;
            }
        };
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.setSelectedItemId(R.id.action_track);
    }

    public void lockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }


    public void unlockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}