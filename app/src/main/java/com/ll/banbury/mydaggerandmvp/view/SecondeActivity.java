package com.ll.banbury.mydaggerandmvp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.ll.banbury.mydaggerandmvp.MyApplication;
import com.ll.banbury.mydaggerandmvp.R;
import com.ll.banbury.mydaggerandmvp.Utils;
import com.ll.banbury.mydaggerandmvp.adapter.SecondAdapter;
import com.ll.banbury.mydaggerandmvp.contract.SecondeActivityContract;
import com.ll.banbury.mydaggerandmvp.dagger.component.DaggerSecondActivityComponent;
import com.ll.banbury.mydaggerandmvp.dagger.module.SecondActivityModule;
import com.ll.banbury.mydaggerandmvp.model.netbean.Baisibudejie;
import com.ll.banbury.mydaggerandmvp.presenter.SecondeActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import tcking.github.com.giraffeplayer2.DefaultPlayerListener;
import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.VideoView;

public class SecondeActivity extends AppCompatActivity implements SecondeActivityContract.View, SwipeRefreshLayout.OnRefreshListener, SecondAdapter.OnItemClickListener {
    @Inject
    SecondeActivityPresenter presenter;
    @Inject
    @Named("default")
    SharedPreferences sharedPreferences;
    @Inject
    Utils utils;


    private SwipeRefreshLayout srl;
    List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = new ArrayList<>();
    private SecondAdapter secondAdapter;
    int page;
    private final String SECOND_PAGE = "secondePage";
    private VideoView video_view;
    private DefaultPlayerListener playerListener = new DefaultPlayerListener() {
        @Override
        public void onCompletion(GiraffePlayer giraffePlayer) {
            video_view.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupActivityComponent();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        video_view = (VideoView) findViewById(R.id.video_view);
        //设置刷新时动画的颜色，可以设置4个
        srl.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        srl.setOnRefreshListener(this);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        secondAdapter = new SecondAdapter(contentlist);
        secondAdapter.setOnItemClickListener(this);
        video_view.setPlayerListener(playerListener);
        rv.setAdapter(secondAdapter);
        page = sharedPreferences.getInt(SECOND_PAGE, 1);
        presenter.getBaisibudejie(page);
        srl.setRefreshing(true);
    }

    /**
     * 通过dagger初始化需要初始化的值
     */
    private void setupActivityComponent() {
        DaggerSecondActivityComponent.builder()
                .appComponent(((MyApplication) getApplication()).getAppComponent())
                .secondActivityModule(new SecondActivityModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void showResult(List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen) {
        contentlist.addAll(0, contentlistBeen);
        secondAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void onComplete() {
        srl.setRefreshing(false);
    }

    public void toNext(View view) {
    }

    @Override
    public void onRefresh() {
        presenter.getBaisibudejie(++page);
    }

    @Override
    public void onItemClick(Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean) {
        if (!utils.isNetworkConnected()) {
            utils.showToast("当前网络不可用!!!", true);
            return;
        }
        String video_uri = contentlistBean.getVideo_uri();
        if (TextUtils.isEmpty(video_uri)) {
            video_view.getPlayer().stop();
            video_view.setVisibility(View.GONE);
            String url = contentlistBean.getWeixin_url();
            Uri uri = Uri.parse(url);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        } else {
            if (utils.getNetworkType() != 1) {
                utils.showToast("当前非wifi网络环境下，注意流量使用!!!", true);
            }
            if (video_view.getVideoInfo().getUri() != null && video_view.getPlayer().isPlaying()) {
                video_view.getPlayer().release();
            }
            video_view.setVisibility(View.VISIBLE);
            video_view.setVideoPath(video_uri).getPlayer().start();
//            video_view.setVideoPath("http://58.250.165.228:6999/ssdtSMP/appAudio/SexMachine.mp3").getPlayer().start();
//            video_view.setVideoPath("http://58.250.165.228:6999/ssdtSMP/appAudio/WhatTheyDo.mp3 ").getPlayer().start();
//            video_view.setVideoPath("http://58.250.165.228:6888/ssdtSMP/appVideo/xxxxx.mp4").getPlayer().start();
        }
    }

    @Override
    protected void onDestroy() {
        sharedPreferences.edit().putInt(SECOND_PAGE, page).apply();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (video_view.getVisibility() == View.VISIBLE) {
            video_view.getPlayer().stop();
            video_view.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
