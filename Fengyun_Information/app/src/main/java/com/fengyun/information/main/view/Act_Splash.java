package com.fengyun.information.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.fengyun.information.R;
import com.fengyun.information.main.view.Act_Main;


/**
 * Created by xfeng on 16/2/19.
 */
public class Act_Splash extends Activity {

    Handler timerHandle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        timerHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                toActivity();
            }
        };
        timerHandle.sendMessageAtTime(new Message(), SystemClock.uptimeMillis() + 3000) ;

    }

    private void toActivity(){

        //TODO 根据条件,跳转不同的页面

        Intent intent = new Intent() ;
        intent.setClass(this,Act_Main.class) ;
        this.startActivity(intent);
        finish();

    }
}
