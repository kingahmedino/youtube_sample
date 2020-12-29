package com.app.youtubeapp.ui.courses;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.app.youtubeapp.BuildConfig;
import com.app.youtubeapp.R;
import com.app.youtubeapp.databinding.ActivityCoursesBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class CoursesActivity extends YouTubeBaseActivity {
    private static final String TAG = "CoursesActivity";
    private ActivityCoursesBinding mCoursesBinding;
    private YouTubePlayerView mYouTubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCoursesBinding = DataBindingUtil.setContentView(this, R.layout.activity_courses);
        mYouTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYouTubePlayer();
        mCoursesBinding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYouTubePlayerView.initialize(BuildConfig.API_KEY, mOnInitializedListener);
            }
        });
    }

    private void initializeYouTubePlayer() {
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Success");
                youTubePlayer.loadVideo("2duc77R4Hqw");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(CoursesActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onInitializationFailure: Failure");
            }
        };
    }
}