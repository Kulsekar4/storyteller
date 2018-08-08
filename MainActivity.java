package com.kulas.admin.rhymes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button BtnSearch=(Button)findViewById(R.id.BtnSearch);
        Button BtnAudio=(Button)findViewById(R.id.BtnAudio);
        Button BtnAV=(Button)findViewById(R.id.BtnAV);
        Button BtnHelp=(Button)findViewById(R.id.BtnHelp);
        ImageButton BtnImg=(ImageButton)findViewById(R.id.BtnImg);
        getSupportActionBar().setLogo(R.drawable.home);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        checkFirstRun();


       BtnSearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentS=new Intent(MainActivity.this,SearchActivity.class);
               startActivity(intentS);
           }
       });
        BtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA=new Intent(MainActivity.this,AudioActivity.class);
                startActivity(intentA);
            }
        });
        BtnAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAV=new Intent(MainActivity.this,AVActivity.class);
                startActivity(intentAV);
            }
        });
        BtnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHelp=new Intent(MainActivity.this,HelpActivity.class);
                startActivity(intentHelp);
            }
        });

        BtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentI=new Intent(MainActivity.this,KidsActivity.class);
                startActivity(intentI);
            }
        });







    }
    public void checkFirstRun() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
            new AlertDialog.Builder(this).setTitle("Popping Rabit").setMessage("Click the below image to enjoy secret POPPING RABIT game!!!").setNeutralButton("OK", null).show();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.kuls,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shareapp:
                Intent intentI = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intentI);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }