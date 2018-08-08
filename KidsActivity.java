package com.kulas.admin.rhymes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class KidsActivity extends AppCompatActivity {
    ImageView kula1,kula2,kula3;
    TextView tv_left,tv_score;
    Button button;
    Random r;
    TextToSpeech t1;

    int score=0,fps=1000,left=5,temifleft=0;
    int which=0;
    int whichsave=0;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kids_activity);
        button=(Button)findViewById(R.id.button);
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_score=(TextView)findViewById(R.id.tv_score);
        kula1=(ImageView)findViewById(R.id.kula1);
        kula2=(ImageView)findViewById(R.id.kula2);
        kula3=(ImageView)findViewById(R.id.kula3);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });



        r=new Random();

        kula1.setVisibility(View.INVISIBLE);
        kula2.setVisibility(View.INVISIBLE);
        kula3.setVisibility(View.INVISIBLE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                left = 5;
                tv_left.setText("LEFT:" + left);
                score = 0;
                tv_score.setText("SCORE: " + score);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        theGameActions();
                    }
                },1000);
                button.setEnabled(false);

            }
        });
        kula1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temifleft=1;
                kula1.setImageResource(R.drawable.hit);
                score=score+1;
                t1.speak(String.valueOf(score), TextToSpeech.QUEUE_FLUSH, null);
                tv_score.setText("SCORE: "+score);
                kula1.setEnabled(false);

            }
        });
        kula3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temifleft=1;
                kula3.setImageResource(R.drawable.hit);
                score=score+1;
                t1.speak(String.valueOf(score), TextToSpeech.QUEUE_FLUSH, null);
                tv_score.setText("SCORE: "+score);
                kula3.setEnabled(false);


            }
        });
        kula2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                temifleft=1;
                kula2.setImageResource(R.drawable.hit);
                score=score+1;
                t1.speak(String.valueOf(score), TextToSpeech.QUEUE_FLUSH, null);
                tv_score.setText("SCORE: "+score);
                kula2.setEnabled(false);

            }
        });
    }

    private void theGameActions(){
        if(score<10){
            fps=1000;
        }
        else if(score>=10&&score<15){
            fps=950;
        }
        else if(score>=15&&score<20){
            fps=900;
        }
        else if(score>=20&&score<25){
            fps=850;
        }
        else if(score>=25&&score<30){
            fps=800;
        }
        else if(score>=30&&score<35){
            fps=750;
        }
        else if(score>=35&&score<40){
            fps=700;
        }
        else if(score>=40&&score<45){
            fps=650;
        }
        else if(score>=45&&score<50){
            fps=600;
        }
        else if(score>=50&&score<55){
            fps=550;
        }
        else if(score>=55&&score<60){
            fps=500;
        }
        else if(score>=60&&score<65){
            fps=450;
        }
        else if(score>=65&&score<70){
            fps=400;
        }
        else if(score>=70&&score<75){
            fps=350;
        }
        else if(score>=75){
            fps=300;
        }


        AnimationDrawable abc =(AnimationDrawable)ContextCompat.getDrawable(this,R.drawable.animation);
        do{
            which=r.nextInt(3)+1;
        }while(whichsave==which);
        whichsave=which;
        if(which==1){
            kula1.setImageDrawable(abc);
            kula1.setVisibility(View.VISIBLE);
            kula1.setEnabled(true);
        }
        else if(which==2){
            kula2.setImageDrawable(abc);
            kula2.setVisibility(View.VISIBLE);
            kula2.setEnabled(true);
        }
        else if(which==3){
            kula3.setImageDrawable(abc);
            kula3.setVisibility(View.VISIBLE);
            kula3.setEnabled(true);
        }
        abc.start();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                kula1.setVisibility(View.INVISIBLE);
                kula2.setVisibility(View.INVISIBLE);
                kula3.setVisibility(View.INVISIBLE);

                kula1.setEnabled(false);
                kula2.setEnabled(false);
                kula3.setEnabled(false);

                if(temifleft==0){
                    left=left-1;
                    if(temifleft==0){
                        final MediaPlayer blop2=MediaPlayer.create(KidsActivity.this, R.raw.blop2);
                        blop2.start();

                    }
                    tv_left.setText("LEFT:"+left);
                }else if(temifleft==1){

                    temifleft=0;
                }
                if(left==0){
                    Toast.makeText(KidsActivity.this,"GAME OVER",Toast.LENGTH_LONG).show();
                    button.setEnabled(true);
                }else if(left>0){
                    theGameActions();
                }
            }
        },fps);

    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent objEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, objEvent);
    }

    @Override
    public void onBackPressed() {

        goBack();
    }

    public void goBack() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                KidsActivity.this);

        alertDialog.setTitle("");
        alertDialog.setMessage("Are you sure you want to exit?");

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
}

