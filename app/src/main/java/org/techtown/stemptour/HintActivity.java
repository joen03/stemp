package org.techtown.stemptour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;



public class HintActivity extends Fragment {

    static TextView hint1;
    static TextView hint2;
    static TextView hint3;

    Button hint_btn1;
    Button hint_btn2;
    Button hint_btn3;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static HintActivity newInstance() {
        return new HintActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hint, container, false);

        hint1 = view.findViewById(R.id.hint_tv1);
        hint2 = view.findViewById(R.id.hint_tv2);
        hint3 = view.findViewById(R.id.hint_tv3);

        hint_btn1 = (Button) view.findViewById(R.id.hint_btn1);
        hint_btn2 = (Button) view.findViewById(R.id.hint_btn2);
        hint_btn3 = (Button) view.findViewById(R.id.hint_btn3);

        hint1.setVisibility(View.VISIBLE);
        hint2.setVisibility(View.GONE);
        hint3.setVisibility(View.GONE);

        hint_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hint1.setVisibility(View.VISIBLE);
                hint2.setVisibility(View.GONE);
                hint3.setVisibility(View.GONE);
            }
        });
        hint_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*hint1.setVisibility(View.GONE);
                hint2.setVisibility(View.VISIBLE);
                hint3.setVisibility(View.GONE);*/
                ((MainActivity) getActivity()).adMob_hint(2);
                ((MainActivity) getActivity()).showRewardedVideoAd();
            }
        });
        hint_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*hint1.setVisibility(View.GONE);
                hint2.setVisibility(View.GONE);
                hint3.setVisibility(View.VISIBLE);*/
                ((MainActivity) getActivity()).adMob_hint(3);
                ((MainActivity) getActivity()).showRewardedVideoAd();
            }
        });

        return view;
    }
}