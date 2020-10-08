package org.techtown.stemptour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StoreActivity extends Fragment {

    TextView st_home,st_bast,st_brand;

    TextView st_main,st_main2,st_main3;

    ViewGroup viewGroup;
    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.store,container,false);

        st_home=viewGroup.findViewById(R.id.st_home);
        st_bast=viewGroup.findViewById(R.id.st_bast);
        st_brand=viewGroup.findViewById(R.id.st_brand);

        st_main=viewGroup.findViewById(R.id.st_main);
        st_main2=viewGroup.findViewById(R.id.st_main2);
        st_main3=viewGroup.findViewById(R.id.st_main3);

        st_main.setVisibility(View.VISIBLE);
        st_main2.setVisibility(View.GONE);
        st_main3.setVisibility(View.GONE);

        st_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_main.setVisibility(View.VISIBLE);
                st_main2.setVisibility(View.GONE);
                st_main3.setVisibility(View.GONE);
            }
        });
        st_bast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_main.setVisibility(View.GONE);
                st_main2.setVisibility(View.VISIBLE);
                st_main3.setVisibility(View.GONE);
            }
        });
        st_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st_main.setVisibility(View.GONE);
                st_main2.setVisibility(View.GONE);
                st_main3.setVisibility(View.VISIBLE);
            }
        });

        return viewGroup;
    }
}
