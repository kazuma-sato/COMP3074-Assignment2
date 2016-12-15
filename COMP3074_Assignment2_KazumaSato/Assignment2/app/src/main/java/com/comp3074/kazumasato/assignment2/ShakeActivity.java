package com.comp3074.kazumasato.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by kazuma.sato on 2016-12-15.
 */
public class ShakeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_activity);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
    }
}
