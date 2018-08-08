package com.kulas.admin.rhymes;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Admin on 1/21/2017.
 */

public class AVActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    ImageButton imageButton1,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_activity);
        imageButton1=(ImageButton)findViewById(R.id.imageButton1);
        imageButton2=(ImageButton)findViewById(R.id.imageButton2);
        imageButton3=(ImageButton)findViewById(R.id.imageButton3);
        imageButton4=(ImageButton)findViewById(R.id.imageButton4);
        imageButton5=(ImageButton)findViewById(R.id.imageButton5);
        imageButton6=(ImageButton)findViewById(R.id.imageButton6);
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtubePlayerView);
        onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            switch (i){
                case 1:
                    youTubePlayer.loadVideo("Qgt6yH8pe7Y");//been
                    break;
                case 2:
                    youTubePlayer.loadVideo("eoW-e0i4AmE");//akbar
                    break;
                case 3:
                    youTubePlayer.loadVideo("9gZL1YIpXME");//tom
                    break;
                case 4:
                    youTubePlayer.loadVideo("1zLO8-9tsw4");//bheem
                    break;
                case 5:
                    youTubePlayer.loadVideo("2fu_7OAuCZY");//dora
                    break;
                case 6:
                    youTubePlayer.loadVideo("ojbx5FYYKTM");//mickey
                    break;
            }

//mickey


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        imageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=1;
                youTubePlayerView.initialize(PlayerConfig.Api_Key,onInitializedListener);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=2;
                youTubePlayerView.initialize(PlayerConfig.Api_Key,onInitializedListener);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=3;
                youTubePlayerView.initialize(PlayerConfig.Api_Key,onInitializedListener);
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=4;
                youTubePlayerView.initialize(PlayerConfig.Api_Key,onInitializedListener);
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=5;
                youTubePlayerView.initialize(PlayerConfig.Api_Key,onInitializedListener);
            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=6;
                youTubePlayerView.initialize(PlayerConfig.Api_Key,onInitializedListener);
            }
        });
    }
}
