package com.example.webviewwithfragment.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.webviewwithfragment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private BottomNavigationView bottomNavigationView;
    private Fragment f1, f2, f3,f4;
    private FragmentManager fragmentManager;

    String previousFragment ="tag1";
    String currentFragment = "tag1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        if(getSupportFragmentManager().findFragmentByTag("tag1") !=null){
            f1 = getSupportFragmentManager().findFragmentByTag("tag1");
        }else{
            f1 = new Fragment1();
        }

        if(getSupportFragmentManager().findFragmentByTag("tag2") !=null){
            f2 = getSupportFragmentManager().findFragmentByTag("tag2");
        }else{
            f2 = new Fragment2();
        }

        if(getSupportFragmentManager().findFragmentByTag("tag3") !=null){
            f3 = getSupportFragmentManager().findFragmentByTag("tag3");
        }else{
            f3 = new Fragment3();
        }

        if(getSupportFragmentManager().findFragmentByTag("tag4") !=null){
            f4 = getSupportFragmentManager().findFragmentByTag("tag4");
        }else{
            f4 = new Fragment4();
        }


        setUpBottomNavigationBar();
        setFragment(f1,"tag1");
    }

    private void setUpBottomNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.frag1:
                        setFragment(f1,"tag1");
                        break;
                    case R.id.frag2:
                        setFragment(f2,"tag2");
                        break;
                    case R.id.frag3:
                        setFragment(f3,"tag3");
                        break;
                    case R.id.frag4:
                        setFragment(f4,"tag4");
                        break;
                }
                return true;
            }
        });
    }
    public void setFragment(Fragment fragment, String tag) {
        previousFragment = currentFragment;
        currentFragment = tag;
        if(getSupportFragmentManager().findFragmentByTag(tag) == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.framelayout, fragment,tag);
            fragmentTransaction.commit();
        }else{

            if(!currentFragment.equals(previousFragment)){
                fragmentManager.beginTransaction().detach(getSupportFragmentManager().findFragmentByTag(previousFragment)).commit();
                fragmentManager.beginTransaction().attach(getSupportFragmentManager().findFragmentByTag(currentFragment)).commit();
            }


           /*  currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName();
            Log.d("BackStack","\n-------------------------\n");
             Log.d("BackStack","\nCurrent : "+currentFragment);
            Log.d("BackStack","\nnext : "+nextFragment);
            Log.d("BackStack","\n-------------------------\n");
            if(!currentFragment.equals(nextFragment)){
                fragmentManager.beginTransaction().detach(getSupportFragmentManager().findFragmentByTag(currentFragment)).commit();
                fragmentManager.beginTransaction().attach(getSupportFragmentManager().findFragmentByTag(nextFragment)).commit();
                currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName();
            }*/
        }
    }

    @Override
    public void onBackStackChanged() {
        for(int i =fragmentManager.getBackStackEntryCount()-1; i>=0; i-- ){
            Log.d("BackStack",fragmentManager.getBackStackEntryAt(i).getName());
        }
        Log.d("BackStack","\n-------------------------\n");
    }
}