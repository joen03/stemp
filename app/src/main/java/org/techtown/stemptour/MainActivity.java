package org.techtown.stemptour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ChooseActivity choose;
    PartyActivity party;
    StoreActivity store;
    InfoActivity info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_Navigation);

        choose = new ChooseActivity();
        party = new PartyActivity();
        store = new StoreActivity();
        info = new InfoActivity();

        //제일 처음 띄워줄 뷰
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,choose).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.choose:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.fragment_layout,choose).commitAllowingStateLoss();
                        return true;
                    } case R.id.party:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.fragment_layout,party).commitAllowingStateLoss();
                        return true;
                    } case R.id.store:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.fragment_layout,store).commitAllowingStateLoss();
                        return true;
                    } case R.id.info:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.fragment_layout,info).commitAllowingStateLoss();
                        return true;
                    }
                    default: return false;
                }

            }
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment).commit();
    }

}