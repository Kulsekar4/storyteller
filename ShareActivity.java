package com.kulas.admin.rhymes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Admin on 4/19/2017.
 */

public class ShareActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "The Story Teller");
            String sAux = "\nEducation + Entertainment = The Story Teller for kids. Lets play,learn and share\n\n";
            sAux = sAux + "http://play.google.com/store/apps/details?id=com.kulas.admin.rhymes";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i,"Choose One"));
        } catch(Exception e) {
            //e.toString();
        }

    }
}