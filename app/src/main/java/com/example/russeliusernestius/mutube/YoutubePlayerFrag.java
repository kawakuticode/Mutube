package com.example.russeliusernestius.mutube;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerFrag extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static String YoutubeAPIKey = "AIzaSyBxSE61XG4570uPVo5YfQkf_13z88RKMIk";
    private String videoId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_youtube_player);

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        videoId = getIntent().getExtras().getString("VIDEO_ID");
        youTubeView.initialize(YoutubeAPIKey, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (null == youTubePlayer)
            return;


        if (!b) {
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            youTubePlayer.loadVideo(videoId);
            youTubePlayer.play();
        }

    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        Toast.makeText(getApplicationContext(), "Video error make sure you have youtube app installed", Toast.LENGTH_LONG).show();

    }
}
