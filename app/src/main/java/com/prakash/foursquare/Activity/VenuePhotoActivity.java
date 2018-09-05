package com.prakash.foursquare.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.prakash.foursquare.R;

import java.util.HashSet;

import com.prakash.foursquare.Util.database.BookmarkedVenueDatabase;

/**
 * Activity to hold VenuePhotoFragment
 */
public class VenuePhotoActivity extends AppCompatActivity {

    public static final String VENUE_ID = "0";
    public static final String VENUE_NAME = "1";
    private BookmarkedVenueDatabase database;
    private ImageButton bookmarkButton;
    private boolean isBookmarkClicked;
    private LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_photo);


        // initialize lottie
        animationView = (LottieAnimationView) findViewById(R.id.lottieClear);

        //setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String venueName = intent.getStringExtra(VENUE_NAME);
        final String venueId = intent.getStringExtra(VENUE_ID);

        // init database
        database = new BookmarkedVenueDatabase(this);
        database.open();
        final HashSet<String> bookmarkedSet = database.getBookmarkedIDs();

        bookmarkButton = (ImageButton) findViewById(R.id.bookmark_button);

        // Change icon when a venue is bookmarked
        if(bookmarkedSet.contains(venueId)) {
            bookmarkButton.setImageResource(R.drawable.baseline_favorite_red_24dp);
            isBookmarkClicked = true;
        } else{
            bookmarkButton.setImageResource(R.drawable.baseline_favorite_border_black_24dp);
        }

        // onClick handler
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isBookmarkClicked) {
                    database.insertVenue(venueId, venueName);
                    Toast.makeText(getApplicationContext(),"Bookmark added",Toast.LENGTH_SHORT).show();
                    bookmarkButton.setImageResource(R.drawable.baseline_favorite_red_24dp);
                    isBookmarkClicked = true;
                    displayAnimation("fav_anim.json");
                } else {
                    database.deleteVenue(venueId);
                    Toast.makeText(getApplicationContext(),"Bookmark removed",Toast.LENGTH_SHORT).show();
                    bookmarkButton.setImageResource(R.drawable.baseline_favorite_border_black_24dp);
                    isBookmarkClicked = false;
                    displayAnimation("unfav_anim_.json");

                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        database.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * This a method to display the lottie animation
     * @param animFileName is passed on to this method so that users can select their desired file
     */
    private void displayAnimation(final String animFileName){
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                LottieComposition.Factory.fromAssetFileName(getApplicationContext(), animFileName, new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(@Nullable LottieComposition composition) {
                        animationView.setComposition(composition);
                        animationView.playAnimation();
                    }
                });

                animationView.addAnimatorListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationView.setVisibility(View.GONE);

                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        animationView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }


}
