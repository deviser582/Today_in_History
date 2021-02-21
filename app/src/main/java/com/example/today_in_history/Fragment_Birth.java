package com.example.today_in_history;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import javax.net.ssl.HttpsURLConnection;


public class Fragment_Birth extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<RvData> list = new ArrayList<>();
    private RV_Adapter rv_adapter = new RV_Adapter(list);
    private Context context;

    private Handler mHandler;
    private Handler tHandler;

    public Fragment_Birth() {
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

        sendRequestWithHttpURLConnection();


        //获取子线程返回的数据并做处理
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                jsonDecode((String)msg.obj);
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //将recyclerview传进来
        View view = inflater.inflate(R.layout.fragment__birth, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.rv_birth);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(rv_adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    //网络请求
    private void sendRequestWithHttpURLConnection() {
        final String mUrl = "https://v1.alapi.cn/api/eventHitory";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    String responseData = StreamToString(in);

                    //返回数据给主线程
                    Message msg = new Message();
                    msg.obj = responseData;
                    mHandler.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }

    private String StreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();
        String oneline;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try{
            while ((oneline = reader.readLine()) != null){
                sb.append(oneline).append('\n');
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //数据解析
    private void jsonDecode(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArraydatas = jsonObject.getJSONArray("data");
            for (int i=0;i < jsonArraydatas.length();i++) {
                JSONObject jsonObjectdata = jsonArraydatas.getJSONObject(i);
                String year = jsonObjectdata.getString("year");
                String monthday = jsonObjectdata.getString("monthday");
                String title = jsonObjectdata.getString("title");
                String desc = jsonObjectdata.getString("desc");
                String type = jsonObjectdata.getString("type");
                //判断类型，并更新recyclerview
                if (type.equals("birth")) {
                    list.add(new RvData(year+"年", monthday, type, title, desc));
                    rv_adapter.notifyDataSetChanged();
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
