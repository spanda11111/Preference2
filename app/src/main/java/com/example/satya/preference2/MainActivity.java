package com.example.satya.preference2;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("firstTime",0);
        int counter = sp.getInt("counter",0);
        if(counter==1)
        {
            //user returning user ,so open second fragment
            FragmentTwo fragmentTwo = new FragmentTwo() ;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction .add(R.id.container1,fragmentTwo);
            fragmentTransaction.commit();
            return;

        }
        //load fragmentone
        FragmentOne fragmentOne = new FragmentOne();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container1,fragmentOne);
        fragmentTransaction.commit();
        //enter into preference
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("counter",1);
        editor.commit();
        //After 5 sec load SecondFragment
        new Handler(). postDelayed(new Runnable() {
            @Override
            public void run() {
                //Android os will come here
                FragmentTwo fragmentTwo  = new FragmentTwo() ;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container1,fragmentTwo);
                fragmentTransaction.commit();
            }
        },5000);
    }
}
