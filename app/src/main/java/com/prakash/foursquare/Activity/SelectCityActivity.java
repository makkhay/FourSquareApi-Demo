package com.prakash.foursquare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.prakash.foursquare.R;
import com.prakash.foursquare.Adapter.SelectCityAdapter;
import com.prakash.foursquare.Util.VerticalSpaceItemDecorator;

import java.util.ArrayList;

/**
 *
 * MainActivity of the app where user selects which city he likes to explore from a list
 */
public class SelectCityActivity extends AppCompatActivity {

    public static final String IS_FIRST_TIME= "isFirstTime";
    public static final String EXTRA_CITY_NAME = "0";
    private RecyclerView cityListView;
    private  SelectCityAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // data to populate the RecyclerView
        ArrayList<String> cityNames = new ArrayList<>();
        cityNames.add("San Francisco, CA");
        cityNames.add("San Diego, CA");
        cityNames.add("Oakland, CA");
        cityNames.add("New York City, NY");
        cityNames.add("Santa Cruz, CA");

        // data to populate the RecyclerView
        ArrayList<Integer> myImageList = new ArrayList<>();
        myImageList.add(R.drawable.sf);
        myImageList.add(R.drawable.sandiego);
        myImageList.add(R.drawable.la);
        myImageList.add(R.drawable.ny);
        myImageList.add(R.drawable.cruz);


        cityListView = findViewById(R.id.city_listview);
        myAdapter = new SelectCityAdapter(SelectCityActivity.this, cityNames, myImageList);

        //  Initialize ItemAnimator, LayoutManager and ItemDecorators
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        int verticalSpacing = 3;
        VerticalSpaceItemDecorator itemDecorator = new VerticalSpaceItemDecorator(verticalSpacing);


        //  Set the LayoutManager
        cityListView.setLayoutManager(layoutManager);
        //  Set the ItemDecorators
        cityListView.addItemDecoration(itemDecorator);
        cityListView.setAdapter(myAdapter);

    }


}
