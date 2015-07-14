package com.daijiale.wear.partyshaking;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by JialeDai on 15/5/17.
 */

public class ActivityToActivity extends Activity {
    private static final int MainActivity = 1;
    private static final int SecondActivity = 2;

    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//
//            }
//        });
        setContentView(R.layout.round_activity_main);


        final ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
        final  ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);

        btn1.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ActivityToActivity.this, FirstActivity.class);
                startActivityForResult(intent, MainActivity);
            }
        });

        btn2.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ActivityToActivity.this, SecondActivity.class);
                startActivityForResult(intent, SecondActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case MainActivity:
                if (resultCode == RESULT_OK) {
                    Uri uriData = data.getData();
                    textView.setText(uriData.toString());
                }
                break;
            case SecondActivity:
                break;
        }

    }
}


