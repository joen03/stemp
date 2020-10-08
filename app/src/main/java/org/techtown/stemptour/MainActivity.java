package org.techtown.stemptour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ChooseActivity choose;
    PartyActivity party;
    StoreActivity store;
    InfoActivity info;
    GameActivity game;

    private RewardedVideoAd mRewardedVideoAd;

    static int hint_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkSelfPermission();

        bottomNavigationView = findViewById(R.id.bottom_Navigation);

        choose = new ChooseActivity();
        party = new PartyActivity();
        store = new StoreActivity();
        info = new InfoActivity();
        game = new GameActivity();

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

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/5224354917");

        // Use an activity context to get the rewarded video instance.
        initRewardedVideoAd();

        //로그인 정보가 없으면 로그인 페이지로 연결
        /*Intent login_intent= new Intent(this, LoginActivity.class);
        startActivity(login_intent);

        finish();*/
    }

    public void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
        //addTestDevice 로 테스트할 기기의 디바이스 ID 를 추가해줘야 한다.
        //테스트 단계에서 실제 동영상을 계속해서 호출할 경우 애드몹으로부터 불이익을 당할 수 있다.
        //자신이 사용하고있는 디바이스의 ID 는 USB 디버깅을 할 때 로그캣을 확인하면 된다.
        /*mRewardedVideoAd.loadAd(getResources().getString(R.string.google_rewarded_ad_id), new AdRequest.Builder().
                addTestDevice(AdRequest.DEVICE_ID_EMULATOR).
                build());*/
    }

    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,fragment).commitAllowingStateLoss();

        //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.fragment_layout, fragment).commit();
    }

    public void initRewardedVideoAd() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                //광고를 성공적으로 불러올 경우 호출되는 코드.
            }

            @Override
            public void onRewardedVideoAdOpened() {
                //광고가 화면상에 나타날때 호출되는 코드. onRewardedVideoStarted 보다 빠르게 호출된다.
            }

            @Override
            public void onRewardedVideoStarted() {
                //광고가 시작할때 호출되는 코드.
            }

            @Override
            public void onRewardedVideoAdClosed() {
                //광고를 닫아버리면 호출되는 코드. 광고를 소진했기 때문에 다음 광고를 미리 불러온다.
                loadRewardedVideoAd();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                //성공적으로 광고가 끝날 경우 호출. 보상정보가 rewardItem 에 묶여서 전달된다.
                //nativeCallBackSuccessRewardedVideoAd(rewardItem.getType(), rewardItem.getAmount());
                if (hint_number==2){
                    HintActivity.hint1.setVisibility(View.GONE);
                    HintActivity.hint2.setVisibility(View.VISIBLE);
                    HintActivity.hint3.setVisibility(View.GONE);
                }else {
                    HintActivity.hint1.setVisibility(View.GONE);
                    HintActivity.hint2.setVisibility(View.GONE);
                    HintActivity.hint3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                //광고시청 도중에 앱을 종료할 경우 호출되는 코드.
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                //동영상광고를 불러올 수 없을때 호출되는 코드.
            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });

        loadRewardedVideoAd();
    }

    public void showRewardedVideoAd() {
        if (mRewardedVideoAd.isLoaded())
            mRewardedVideoAd.show();
    }

    /*public static void Native_ShowRewardedVideoAd() {
        appActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                appActivity.showRewardedVideoAd();
            }
        });
    }*/

    public void adMob_hint(int number){
        hint_number = number;
    }

    //권한
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    Log.d(" ", "권한 허용 : " + permissions[i]);
                }
            }
        }

    }

    public void checkSelfPermission() {
        String temp = "";
        //파일 쓰기 권한 확인
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.ACCESS_NETWORK_STATE + " ";
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.INTERNET + " ";
        }
        if (TextUtils.isEmpty(temp) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
        }else {
            // 모두 허용 상태
            Toast.makeText(this, "권한 허용", Toast.LENGTH_SHORT).show();
        }
    }
}