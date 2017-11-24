package com.ll.banbury.mydaggerandmvp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ll.banbury.mydaggerandmvp.R;
import com.ll.banbury.mydaggerandmvp.model.netbean.WinXinJX;

import java.util.List;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_16:12.
 * @description
 */

public class MainAdapter extends BaseAdapter {
    private List<WinXinJX.ResultBean.ListBean> list;

    public MainAdapter(List<WinXinJX.ResultBean.ListBean> list) {
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public WinXinJX.ResultBean.ListBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHold viewHold = null;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_main, null);
            viewHold = new ViewHold(view);
            view.setTag(viewHold);
        }
        viewHold = (ViewHold) view.getTag();
        WinXinJX.ResultBean.ListBean listBean = list.get(i);
        viewHold.textView1.setText(listBean.getTitle());
        viewHold.textView2.setText(listBean.getSource());
        Glide.with(viewGroup.getContext()).load(listBean.getFirstImg()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHold.imageView);
        return view;
    }

    class ViewHold {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public ViewHold(View view) {
            imageView = view.findViewById(R.id.iv);
            textView1 = view.findViewById(R.id.tv1);
            textView2 = view.findViewById(R.id.tv2);
        }
    }
}
