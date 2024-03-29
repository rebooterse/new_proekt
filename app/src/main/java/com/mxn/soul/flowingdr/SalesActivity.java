package com.mxn.soul.flowingdr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import adapters.SalesRecViewAdapter;
import fragments.MenuListFragment;

@SuppressLint("Registered")
public class SalesActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private FlowingDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //  rvFeed = (RecyclerView) findViewById(R.id.rvFeed);
     //  mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);

     //  rvFeed = (RecyclerView) findViewById(R.id.rvFeed);
     //  mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
     //  mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
//        setupToolbar();
//        setupFeed();
        setupMenu();
    }

//    protected void setupToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDrawer.toggleMenu();
//            }
//        });
//    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);
        SalesRecViewAdapter salesRecViewAdapter = new SalesRecViewAdapter(this);
        rvFeed.setAdapter(salesRecViewAdapter);
        salesRecViewAdapter.updateItems();
    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
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
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
