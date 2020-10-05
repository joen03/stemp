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

import java.util.ArrayList;

public class PartyActivity extends Fragment {

    private ArrayList<String> list;
    ListView listView;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.party, container, false);

        list = new ArrayList<String>();
        listView= (ListView) view.findViewById(R.id.party_list);
        adapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        list.add("test1");
        list.add("test2");
        list.add("test3");

        adapter.notifyDataSetChanged();

        return view;
    }
}
