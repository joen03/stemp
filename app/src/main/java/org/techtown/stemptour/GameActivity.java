package org.techtown.stemptour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GameActivity extends Fragment {

    private ArrayList<String> list;
    ListView listView;
    private ArrayAdapter<String> adapter;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static GameActivity newInstance() {
        return new GameActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_game, container, false);

        list = new ArrayList<String>();
        listView= (ListView) view.findViewById(R.id.game_list);

        int list_no = ((MainActivity)getActivity()).list_num;

        System.out.println("게임 : "+list_no);

        if (list_no==0){
            list = new ArrayList<String>();
            list.add("경복궁");
            list.add("근정전");
            list.add("경회루");
            list.add("향원정");
        }else if (list_no==1){
            list = new ArrayList<String>();
            list.add("인정전");
            list.add("부용정");
            list.add("돈화문");
        }else if (list_no==2){
            list = new ArrayList<String>();
            list.add("대한문");
            list.add("중화전");
            list.add("중화문");
        }else if (list_no==3){
            list = new ArrayList<String>();
            list.add("홍화문");
            list.add("문정전");
            list.add("명정문");
        }

        adapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainActivity)getActivity()).replaceFragment(HintActivity.newInstance());
            }
        });

        return view;
    }
}