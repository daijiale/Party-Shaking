package com.daijiale.wear.partyshaking;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.daijiale.wear.partyshaking.ShakeListener.OnShakeListener;

import java.util.Random;

/**
Created by JialeDai on 15/5/17.
 **/

public class FirstActivity extends Activity {

    private TextView mTextView;
    private Vibrator vibrator;

    private ImageView imView, imcount, imgnot;

    private ShakeListener shakeListener;

    private	int ico[] = { R.drawable.image_left, R.drawable.image_middle,R.drawable.image_right };

    private	int icoSotp[] = { R.drawable.lottery_result,R.drawable.game1,R.drawable.game2,
            R.drawable.game3,R.drawable.game4,R.drawable.game5};

    private	int index = 0;

    private int randomC=0;

    private Random random;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shakingstatus1);
        init();


    }

    public void init(){
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        shakeListener = new ShakeListener(this);
        shakeListener.setOnShake(onShake);

        imView = (ImageView) this.findViewById(R.id.imgmiddle);
        imcount = (ImageView) this.findViewById(R.id.imgtit);
        imgnot = (ImageView) this.findViewById(R.id.imgnoth);
        random=new Random();
    }



    private OnShakeListener onShake = new OnShakeListener() {

       @Override
       public void onShake() {
           imgnot.setVisibility(View.GONE);
            startVibrator();
            shakeListener.stop();

            handler.sendEmptyMessageDelayed(1, 200);
            handler.sendEmptyMessageDelayed(2, 2000);

            randomC=random.nextInt(6);
            Log.e("--", "---" + randomC);

        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (index < ico.length - 1) {
                   index++;
                } else {
                    index = 0;
               }
                imView.setBackgroundResource(ico[index]);
                handler.sendEmptyMessageDelayed(1, 200);
            } else {
                imView.setBackgroundResource(icoSotp[0]);
                handler.removeMessages(1);
                shakeListener.start();



                if(randomC==5){
                    imgnot.setBackgroundResource(icoSotp[3]);
                    imgnot.setVisibility(View.VISIBLE);
                }else if(randomC==4)
                {
                    imgnot.setBackgroundResource(icoSotp[4]);
                    imgnot.setVisibility(View.VISIBLE);
                }else if(randomC==3)
                {
                    imgnot.setBackgroundResource(icoSotp[5]);
                    imgnot.setVisibility(View.VISIBLE);

                }
                else if(randomC==2)
                {
                    imgnot.setBackgroundResource(icoSotp[1]);
                    imgnot.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgnot.setBackgroundResource(icoSotp[2]);
                    imgnot.setVisibility(View.VISIBLE);
                }

            }
        }

    };


    public void startVibrator() {
        vibrator.vibrate(new long[] { 500, 300, 500, 300 }, -1);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
