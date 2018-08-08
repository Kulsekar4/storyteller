package com.kulas.admin.rhymes;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

import android.widget.TextView;
import android.widget.Toast;


import static com.kulas.admin.rhymes.R.id.enter;

public class AudioActivity extends ActionBarActivity implements OnClickListener, OnInitListener {
//TTS object

    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;

    //create the Activity
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_activity);
        try {
            getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button BtnOpen=(Button)findViewById(R.id.BtnOpen);//file
        Button BtnDelete=(Button)findViewById(R.id.BtnDelete);//file
        Button BtnSave=(Button)findViewById(R.id.BtnSave);//file
        EditText enteredText = (EditText)findViewById(enter);//file
        AutoCompleteTextView EditfileText=(AutoCompleteTextView) findViewById(R.id.editfiletext);//file
        String filename= EditfileText.getText().toString();;//file


        Button speakButton = (Button)findViewById(R.id.speak);
        //listen for clicks
        speakButton.setOnClickListener(this);
        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
    }

   /* public void getPath(){
        AutoCompleteTextView EditfileText=(AutoCompleteTextView) findViewById(R.id.editfiletext);
         String[] countries;
        String path = getFilesDir().toString();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());

        }
        ArrayAdapter adapter=new ArrayAdapter(AudioActivity.this,android.R.layout.select_dialog_item,countries);
        EditfileText.setThreshold(1);
         EditfileText.setAdapter(adapter);



    }*/

    public void getPath() throws IOException {
        AutoCompleteTextView EditfileText=(AutoCompleteTextView) findViewById(R.id.editfiletext);
        ArrayList<String> items = new ArrayList<String>();
        AssetManager assetManager = getApplicationContext().getAssets();
        for (String file : assetManager.list("")) {
            if (file.endsWith(".txt"))
                items.add(file);
        }
        ArrayAdapter adapter=new ArrayAdapter(AudioActivity.this,android.R.layout.select_dialog_item,fileList());
        EditfileText.setThreshold(1);
        EditfileText.setAdapter(adapter);
    }

    public void writeMessage(View view) throws FileNotFoundException {
        EditText enteredText = (EditText)findViewById(enter);
        EditText EditfileText=(EditText)findViewById(R.id.editfiletext);
        String kula=EditfileText.getText().toString();
        String Message=enteredText.getText().toString();

        try {
            FileOutputStream fileOutputStream = openFileOutput(kula, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Message saved",Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void deleteMessage(View view){
        EditText EditfileText=(EditText)findViewById(R.id.editfiletext);
        String kula=EditfileText.getText().toString();

        File dir = getFilesDir();
        File file = new File(dir, kula);
        if(file.exists()) {
            boolean deleted = file.delete();
            Toast.makeText(AudioActivity.this,"File deleted successfully",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(AudioActivity.this,"Sry there is no file under such name",Toast.LENGTH_SHORT).show();


    }
    public void readMessage(View view)  {
        EditText enteredText = (EditText)findViewById(R.id.enter);
        TextView textView=(TextView)findViewById(R.id.intro);
        EditText EditfileText=(EditText)findViewById(R.id.editfiletext);
        String kula=EditfileText.getText().toString();
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput(kula);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            while((Message= bufferedReader.readLine())!=null){
                stringBuffer.append(Message+"\n");
            }
            enteredText.setText(stringBuffer.toString());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // /respond to button clicks
    public void onClick(View v) {

        //get the text entered
        EditText enteredText = (EditText)findViewById(enter);
        String words = enteredText.getText().toString();
        speakWords(words);
    }

    //speak the user text
    private void speakWords(String speech) {

        //speak straight away
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {
        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        myTTS.stop();
        finish();
    }


}
