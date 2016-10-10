package com.example.supriya.card_view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Songs_List extends AppCompatActivity{
    private RecyclerView recyclerView;
    private Songs_List_Adapter adapter;
    private List<Album> albumList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songlist);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);

        albumList = getAllAlbums();
        adapter = new Songs_List_Adapter(this, albumList);
        adapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);


    }

    private List<Album> getAllAlbums() {
        String albumID = null;
        Bundle extras=getIntent().getExtras();
        if (extras!=null){
            albumID=extras.getString("ALBUM_ID");
        }
        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+" !=0";
        // String[] projection = {MediaStore.Audio.Media.DATA , MediaStore.Audio.Media.DISPLAY_NAME};
        List<Album> listOfAlbums=new ArrayList<>();

       // String selection2 = selection + " and "+ MediaStore.Audio.Media.ALBUM_KEY+"="+ albumname;

        String[] projection2= {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM_ID
                // MediaStore.Audio.Albums.NUMBER_OF_SONGS
        };
        int count=0;
        String[] selectionArgs={"Path: "+"%"};

        Cursor cursor=managedQuery(allsongsuri, projection2,selection,
                null,
                MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {count++;}while (cursor.moveToNext());}}

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                    try {
                        String song_name = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        String song_id = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media._ID));
                        String album_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                        String fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));
                        // int album_no_ofsongs = cursor.getInt(cursor
                        //.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS));
                        try {
                            Album album = new Album(song_id, song_name,fullpath);
                            if(album_id==albumID){
                            listOfAlbums.add(album);}
                        } catch (IllegalArgumentException ex) {
                        }
                    }catch (IllegalArgumentException ex){}

                } while (cursor.moveToNext());

            }
        }




        return listOfAlbums;

    }

}
