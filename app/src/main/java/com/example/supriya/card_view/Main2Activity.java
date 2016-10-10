package com.example.supriya.card_view;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Fragment {
    private RecyclerView recyclerView;
    private AdapterClass adapter;
    private List<Album> albumList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_main,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        albumList = getAllAlbums();
        adapter = new AdapterClass(getContext(), albumList);
        adapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        return view;
    }

    private List<Album> getAllAlbums() {
        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // String selection =MediaStore.Audio.Albums.DATA +" like ?";
        // String[] projection = {MediaStore.Audio.Media.DATA , MediaStore.Audio.Media.DISPLAY_NAME};
        List<Album> listOfAlbums=new ArrayList<>();

        String selection2 = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection2= {
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA
                // MediaStore.Audio.Albums.NUMBER_OF_SONGS
        };
        int count=0;
        String[] selectionArgs={"Path: "+"%"};

        Cursor cursor=getActivity().managedQuery(allsongsuri,projection2,selection2,
                null,
                MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {count++;}while (cursor.moveToNext());}}

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                    try {
                        String album_name = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        String album_id = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                        String fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));
                        // int album_no_ofsongs = cursor.getInt(cursor
                        //.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS));
                        try {
                            Album album = new Album(album_id, album_name,fullpath);
                            listOfAlbums.add(album);
                        } catch (IllegalArgumentException ex) {
                        }
                    }catch (IllegalArgumentException ex){}

                } while (cursor.moveToNext());

            }
        }




        return listOfAlbums;

    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
  */

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}

