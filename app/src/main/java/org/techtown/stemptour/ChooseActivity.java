package org.techtown.stemptour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class ChooseActivity extends Fragment {

    private ArrayList<String> list;
    ListView listView;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> list2;
    ListView listView2;
    private ArrayAdapter<String> adapter2;

    public static Integer list_no;

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

        list.add("서울");
        list.add("파주");
        list.add("가평");

        adapter.notifyDataSetChanged();

        list2 = new ArrayList<String>();
        listView2= (ListView) view.findViewById(R.id.choose2_list);
        adapter2 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
        listView2.setAdapter(adapter2);

        list2.add("경복궁");
        list2.add("창덕궁");
        list2.add("덕수궁");
        list2.add("창경궁");

        adapter2.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("리스트 넘버1 : "+view);
                System.out.println("리스트 넘버2 : "+i);
                System.out.println("리스트 넘버3 : "+l);
                if (i==0){
                    list2 = new ArrayList<String>();
                    list2.add("경복궁");
                    list2.add("창덕궁");
                    list2.add("덕수궁");
                    list2.add("창경궁");
                    adapter2 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
                    listView2.setAdapter(adapter2);
                }else if (i==1){
                    list2 = new ArrayList<String>();
                    list2.add("임진각 평화 누리공원");
                    list2.add("헤이리 예술마을");
                    list2.add("지혜의 숲");
                    list2.add("감악산 출렁다리");
                    adapter2 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
                    listView2.setAdapter(adapter2);
                }else if (i==2){
                    list2 = new ArrayList<String>();
                    list2.add("아침 고요 수목원");
                    list2.add("에델바이스");
                    list2.add("잣향기 푸른숲");
                    adapter2 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
                    listView2.setAdapter(adapter2);
                }

            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainActivity)getActivity()).list_num(i);
                System.out.println("리스트 넘버 : "+((MainActivity)getActivity()).list_num);
                ((MainActivity)getActivity()).replaceFragment(GameActivity.newInstance());
                //MainActivity.replaceFragment(GameActivity.newInstance());

                //getSupportFragmentManager().beginTransaction() .replace(R.id.fragment_layout,GameActivity.newInstance()).commitAllowingStateLoss();
            }
        });

        /*listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(GameActivity.newInstance());
            }
        });*/

        return view;
    }
}
