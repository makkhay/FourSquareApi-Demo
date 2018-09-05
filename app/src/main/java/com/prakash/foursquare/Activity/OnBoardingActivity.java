package com.prakash.foursquare.Activity;

import android.os.Bundle;


import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.prakash.foursquare.R;
import com.prakash.foursquare.Util.OnBoardingUtil.MyPreferences;
import com.prakash.foursquare.Util.OnBoardingUtil.SampleSlide;

/**
 * First onboarding screen.
 */
public class OnBoardingActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.boarding_one));
        addSlide(SampleSlide.newInstance(R.layout.boarding_two));
        addSlide(SampleSlide.newInstance(R.layout.boarding_three));

        boolean isFirstTime = MyPreferences.isFirst(OnBoardingActivity.this);

        if(!isFirstTime){
            Intent i = new Intent(this, SelectCityActivity.class);
            startActivity(i);
        }


        // Override bar/separator color.
        setBarColor(Color.parseColor("#388E3C"));
        setSeparatorColor(Color.parseColor("#00060c"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

    }


    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Intent i = new Intent(this, SelectCityActivity.class);
        startActivity(i);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        Intent i = new Intent(this, SelectCityActivity.class);
        startActivity(i);

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }


}
