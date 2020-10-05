package org.techtown.stemptour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class ChooseActivity extends Fragment {

    private ArrayList<String> list;
    ListView listView;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> list2;
    ListView listView2;
    private ArrayAdapter<String> adapter2;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static ChooseActivity newInstance() {
        return new ChooseActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose, container, false);

        list = new ArrayList<String>();
        listView= (ListView) view.findViewById(R.id.choose1_list);
        adapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        list.add("test1");
        list.add("test2");
        list.add("test3");

        adapter.notifyDataSetChanged();

        list2 = new ArrayList<String>();
        listView2= (ListView) view.findViewById(R.id.choose2_list);
        adapter2 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
        listView2.setAdapter(adapter2);

        list2.add("test11");
        list2.add("test21");
        list2.add("test31");

        adapter2.notifyDataSetChanged();

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(GameActivity.newInstance());
            }
        });

        return view;
    }
}
