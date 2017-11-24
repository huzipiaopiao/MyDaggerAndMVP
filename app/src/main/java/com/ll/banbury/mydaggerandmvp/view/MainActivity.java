package com.ll.banbury.mydaggerandmvp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ll.banbury.mydaggerandmvp.MyApplication;
import com.ll.banbury.mydaggerandmvp.R;
import com.ll.banbury.mydaggerandmvp.adapter.MainAdapter;
import com.ll.banbury.mydaggerandmvp.model.netbean.WinXinJX;
import com.ll.banbury.mydaggerandmvp.contract.MainActivityContract;
import com.ll.banbury.mydaggerandmvp.dagger.module.MainActivityModule;
import com.ll.banbury.mydaggerandmvp.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    @Inject
    MainActivityPresenter presenter;
    @Inject
    @Named("default")
    SharedPreferences sharedPreferences;

    private ListView lv;
    private SwipeRefreshLayout srl;
    private int page;
    private List<WinXinJX.ResultBean.ListBean> mList = new ArrayList<>();
    private MainAdapter mainAdapter;
    private final String MAIN_PAGE = "main_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivityComponent();
        lv = (ListView) findViewById(R.id.lv);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        //设置刷新时动画的颜色，可以设置4个
        srl.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        srl.setOnRefreshListener(this);
        mainAdapter = new MainAdapter(mList);
        lv.setAdapter(mainAdapter);
        lv.setOnItemClickListener(this);
        page = sharedPreferences.getInt(MAIN_PAGE, 1);
        presenter.getWeixinJx(page);

    }

    private void setupActivityComponent() {
        ((MyApplication) getApplication()).getAppComponent().getMainActivityComponent(new MainActivityModule(this)).injet(this);
    }

    @Override
    public void showResult(WinXinJX result) {
        mList.addAll(0, result.getResult().getList());
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onComplete() {
        srl.setRefreshing(false);
    }

    public void toNext(View view) {
        startActivity(new Intent(this, SecondeActivity.class));
    }

    @Override
    public void onRefresh() {
        presenter.getWeixinJx(++page);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = mainAdapter.getItem(i).getUrl();
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(MAIN_PAGE, page);
        edit.commit();
        super.onDestroy();
    }
}
