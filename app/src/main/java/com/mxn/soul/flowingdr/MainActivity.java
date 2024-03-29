package com.mxn.soul.flowingdr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import adapters.RecyclerViewGalleryMain;
import fragments.MenuListFragment;
import infoFromServer.ClothesFormServer;
import models.Clothes;


public class MainActivity extends ClothesFormServer implements RecyclerViewGalleryMain.OnClickListenerFullScreen,
        RecyclerViewGalleryMain.OnClickListenerShop {

    private FlowingDrawer mDrawer;

    public static final int NUM_COLUMNS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = findViewById(R.id.drawerlayout);
        // Write a message to the database



        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupMenu();
        galleryImages();
    }


    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }



    private void galleryImages() {
        // gallery rv
         List<Clothes> list;
        final RecyclerView myRecyclerView = findViewById(R.id.rv_main_activity);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");
        final List<Clothes> finalList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.child(String.valueOf(i)).getChildren()) {
                    HashMap<String,Clothes> messageMap = (HashMap<String, Clothes>) postSnapshot.getValue();
                    Collection<Clothes> messageItems = messageMap.values() ;

                   finalList.addAll(messageItems);
                   i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        for (int i = 0; i < 10000; i++) {
            Toast.makeText(this,String.valueOf(i),Toast.LENGTH_SHORT);
        }

        RecyclerViewGalleryMain recyclerViewAdapter = new RecyclerViewGalleryMain(finalList
                , MainActivity.this, MainActivity.this,MainActivity.this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemImageFullScreenClick(int position) {

    }

    @Override
    public void onItemImageShopClick(int position) {
        Toast.makeText(this, "Խանութ " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

}
