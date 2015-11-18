/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.example.bannerexample;
/**
 * How the computer knows what your talking about so you dont look crazy
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.view.View;
import android.widget.*;
import android.graphics.Color;


/**
 * Main Activity. Inflates main activity xml and child fragments.
 */
public class AdTrial extends ActionBarActivity
{
    /**
     * Assigning the layout objects to an actual thing
     */
    private AdView trialAd;
    private Button monsterButton;
    private RelativeLayout background;
    private TextView monsterText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        /**
         * this is used when the program opens and lets the screen open
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        trialAd = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        trialAd.loadAd(adRequest);
        /**
         * combining the variable with the buttons and such
         */
        monsterButton = (Button) findViewById(R.id.Random);
        background = (RelativeLayout) findViewById(R.id.Background);
        monsterText = (TextView) findViewById(R.id.monsterText);

        /**
         * assigning a important method so that you can use buttons
         */
        setupListeners();
    }

    private void setupListeners()
    {
        /**
         * this (after you press the button) will change the background to red and change the
         * text to 'You monster' then force closes
         */
    monsterButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            background.setBackgroundColor(Color.rgb(255, 0, 0));
            monsterText.setText("You monster!");
            try
            {
                Thread.sleep(1500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finish();

        }
    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ads, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause()
    {
        if (trialAd != null)
        {
            trialAd.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume()
    {
        super.onResume();
        if (trialAd != null)
        {
            trialAd.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy()
    {
        if (trialAd != null)
        {
            trialAd.destroy();
        }
        super.onDestroy();
    }
}
