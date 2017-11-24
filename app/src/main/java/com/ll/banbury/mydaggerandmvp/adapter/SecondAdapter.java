package com.ll.banbury.mydaggerandmvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ll.banbury.mydaggerandmvp.R;
import com.ll.banbury.mydaggerandmvp.model.netbean.Baisibudejie;

import java.util.List;

import tcking.github.com.giraffeplayer2.DefaultPlayerListener;
import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.PlayerListener;
import tcking.github.com.giraffeplayer2.VideoView;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/11/23_17:32.
 * @description
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHold> {


    List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SecondAdapter(List<Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist) {
        this.contentlist = contentlist;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_second, null);
        ViewHold viewHold = new ViewHold(view);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(final ViewHold holder, final int position) {
        final Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = contentlist.get(position);
        holder.tv1.setText(contentlistBean.getText());
        holder.tv2.setText(contentlistBean.getCreate_time());
        Glide.with(holder.iv.getContext()).load(contentlistBean.getImage0()).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv);
        final String video_uri = contentlistBean.getVideo_uri();
        if (!TextUtils.isEmpty(video_uri)) {
            holder.play.setVisibility(View.VISIBLE);
        } else {
            holder.play.setVisibility(View.GONE);
        }
        if (onItemClickListener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        onItemClickListener.onItemClick(contentlistBean);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return contentlist == null ? 0 : contentlist.size();
    }

    class ViewHold extends RecyclerView.ViewHolder {
        public final ImageView iv;
        public final ImageView play;
        public final TextView tv1;
        public final TextView tv2;
        public final View view;

        public ViewHold(View itemView) {
            super(itemView);
            view = itemView;
            iv = (ImageView) itemView.findViewById(R.id.iv);
            play = (ImageView) itemView.findViewById(R.id.play);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Baisibudejie.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean);
    }
}
