package com.example.supriya.card_view;

/**
 * Created by Supriya on 11-09-2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;






/**
 * Created by Supriya on 25-08-2016.
 */
public class Songs_List_Adapter extends RecyclerView.Adapter<Songs_List_Adapter.ViewHolderInner>{
    private Context context;
    private List<Album> albumList;


    public class ViewHolderInner extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public ViewHolderInner(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


        }
    }


    public Songs_List_Adapter(Context context, List<Album> albumList) {
        this.context =context ;
        this.albumList = albumList;
    }


    @Override
    public ViewHolderInner onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_list, parent, false);

        return new ViewHolderInner(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolderInner holder, int position) {
        final Album album = albumList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), My_Player.class);
                intent.putExtra("file_path", album.getFullpath());
                context.startActivity(intent);
            }
        });
        holder.title.setText(album.getName());

        // loading album cover using Glide library
        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

