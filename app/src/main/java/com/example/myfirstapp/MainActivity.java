package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class MainActivity extends AppCompatActivity implements InterstitialListener {
    private final static String KEY = "1006ffcf1";
    private final static String PLACEMENT_NAME = "DefaultInterstitial";
    private Button mInterstitialLoadButton;
    private Button mInterstitialShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIElements();
        startIronSourceInitTask();


    }

    private void initUIElements() {
        mInterstitialLoadButton = (Button) findViewById(R.id.loadInterstitialButton);
        mInterstitialLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IronSource.loadInterstitial();

            }
        });
        mInterstitialShowButton = (Button) findViewById(R.id.showInterstitialButton);
        mInterstitialShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if interstitial is available
                if (IronSource.isInterstitialReady()) {
                    //show the interstitial
                    IronSource.showInterstitial();
                }
                else{

                }
            }
        });
    }

    private void startIronSourceInitTask() {
        IronSource.init(this, KEY, IronSource.AD_UNIT.INTERSTITIAL);
        initIronSource();
    }

    private void initIronSource() {
        IronSource.setInterstitialListener(this);
        IronSource.init(this, KEY);
        updateButtonsState();
    }

    private void updateButtonsState() {
        handleLoadInterstitialButtonState(true);
        handleInterstitialShowButtonState(false);
    }

    public void handleLoadInterstitialButtonState(final boolean available) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInterstitialLoadButton.setEnabled(available);
            }
        });

    }


    public void handleInterstitialShowButtonState(final boolean available) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInterstitialShowButton.setEnabled(available);
            }
        });
    }



    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
        updateButtonsState();
    }
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
        updateButtonsState();
    }


    @Override
    public void onInterstitialAdReady() {
        Log.d("", "onInterstitialAdReady: READY");
        mInterstitialShowButton.setEnabled(true);
    }

    @Override
    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onInterstitialAdOpened() {

    }

    @Override
    public void onInterstitialAdClosed() {

    }

    @Override
    public void onInterstitialAdShowSucceeded() {

    }

    @Override
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onInterstitialAdClicked() {

    }



}