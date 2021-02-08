package com.example.today_in_history;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class Fragment_Birth extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<RvData> list = new ArrayList<>();
    private RV_Adapter rv_adapter = new RV_Adapter(list);
    private Context context;

    public Fragment_Birth() {
        // Required empty public constructor
    }

    public static Fragment_Birth newInstance(String param1, String param2) {
        Fragment_Birth fragment = new Fragment_Birth();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__birth, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.rv_birth);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(rv_adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
        //return inflater.inflate(R.layout.fragment__birth, container, false);
    }

    public void initData(){
        list.add(new RvData("1265年","0208","death","伊利汗国的建立者旭烈兀逝世","旭烈兀（Hulagu或者Hulegu，1217年—1265年2月8日），蒙古族人，成吉思汗之孙、拖雷之子、忽必烈、蒙哥和阿里不哥的兄弟，四人"));
        list.add(new RvData("1587年","0208","death","苏格兰女王玛丽一世被斩首处死","苏格兰女王玛丽一世（Mary Stuart或Mary, Queen of Scots，1542年12月8日－1587年2月8日）是苏格兰的统治者（在位时间1542年12"));
        rv_adapter.notifyDataSetChanged();
    }

}
