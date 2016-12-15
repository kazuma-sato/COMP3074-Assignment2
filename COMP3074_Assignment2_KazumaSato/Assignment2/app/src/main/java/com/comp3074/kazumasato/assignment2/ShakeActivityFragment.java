package com.comp3074.kazumasato.assignment2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by kazuma.sato on 2016-12-15.
 */
public class ShakeActivityFragment extends Fragment{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((TextView)view.findViewById(R.id.Message)).setText("You shook the phone!\nClick back!!");
    }
}
