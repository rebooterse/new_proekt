package com.mxn.soul.flowingdr;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import adapters.RecyclerViewGalleryMain;
import fragments.MenuListFragment;
import infoFromServer.ClothesFormServer;

public class MainActivity extends ClothesFormServer implements RecyclerViewGalleryMain.OnClickListenerFullScreen,
        RecyclerViewGalleryMain.OnClickListenerShop {

    private FlowingDrawer mDrawer;

    public static final int NUM_COLUMNS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = findViewById(R.id.drawerlayout);

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
    // drawer bacel pakeln es xekavarum
//       mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
//           @Override
//           public void onDrawerStateChange(int oldState, int newState) {
//               if (newState == ElasticDrawer.STATE_CLOSED) {
//                   Toast.makeText(MainActivity.this, "change", Toast.LENGTH_SHORT).show();
//               }
//           }

//           @Override
//           public void onDrawerSlide(float openRatio, int offsetPixels) {
//               Toast.makeText(MainActivity.this, "slide", Toast.LENGTH_SHORT).show();
//           }
//       });


    private void galleryImages() {
        // gallery rv
        RecyclerView myRecyclerView = findViewById(R.id.rv_main_activity);
        RecyclerViewGalleryMain recyclerViewAdapter = new RecyclerViewGalleryMain(getClotheList()
                , this, this,this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerView.setAdapter(recyclerViewAdapter);
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
