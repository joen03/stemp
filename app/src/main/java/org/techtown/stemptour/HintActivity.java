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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class HintActivity extends Fragment {

    private ViewFlipper flipper;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static HintActivity newInstance() {
        return new HintActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_game, container, false);

        flipper=(ViewFlipper)view.findViewById(R.id.viewFlipper);

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
            }
        });

        return view;
    }
}