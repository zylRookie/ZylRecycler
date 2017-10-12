package com.zyl.zylrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tc.zyl.httpmanager.HttpManager;
import com.zyl.mlibrary.OnItemClickListener;
import com.zyl.mlibrary.ZylAdapter;
import com.zyl.mlibrary.ZylRViewHolder;
import com.zyl.mlibrary.ZylRecyclerView;
import com.zyl.mlibrary.ZylViewHolder;

import java.util.ArrayList;

/**
 * Author: Zhaoyl
 * Date: 2017/10/11 10:56
 * Description: 类名
 * PackageName: MainActivity
 * Copyright: 同城科技
 **/
public class MainActivity extends AppCompatActivity {

    ZylAdapter<GoodEvaluteModel.DataBean> mAdapter;
    String url = "http://admin.kkptu.com/interfaceDOC/firstProject/PerSongs.ashx";
    ArrayList<GoodEvaluteModel.DataBean> data = new ArrayList<>();
    private ZylRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = ((ZylRecyclerView) findViewById(R.id.rvNewFriend));
        initData();
        setAdapter();
    }

    private void initData() {
        HttpManager.get()
                .url(url)
                .addParams("action", "Get_Profit")
                .addParams("Type", 1 + "")
                .build().execute(new HttpManager.ResponseCallback<GoodEvaluteModel>() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(GoodEvaluteModel o) {
                data.clear();
                data.addAll(o.data);
                if (mAdapter != null)
                    mAdapter.notifyDataSetChangedWrapper();
            }
        });
    }

    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new ZylAdapter<GoodEvaluteModel.DataBean>(MainActivity.this, data, R.layout.item_list) {
                @Override
                public void convert(ZylRViewHolder helper, GoodEvaluteModel.DataBean item, int position) {
                    helper.setText(R.id.tv, data.get(position).pao_name);
                    Glide.with(MainActivity.this).load(data.get(position).Pao_Image).into((ImageView) helper.getView(R.id.iv));
                }
            };
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ZylViewHolder helper, ViewGroup parent, View itemView, int position) {
                    Toast.makeText(MainActivity.this, "hah--->" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        recyclerView.setAdapter(mAdapter);
    }
}
