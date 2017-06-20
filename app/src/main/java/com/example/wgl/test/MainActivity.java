package com.example.wgl.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity /**/ implements HomeFragment.OnFragmentInteractionListener {

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationFragment notificationFragment;

    private AboutFragment aboutFragment;

    public static final String AVATAR_URL = "http://lorempixel.com/200/200/people/1/";

    private DrawerLayout drawerLayout;
    private View content;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initRecyclerView();
        initFab();
        //initToolbar();
        setupDrawerLayout();

        content = findViewById(R.id.content);

        final ImageView avatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.avatar);
        //Picasso.with(this).load(AVATAR_URL).transform(new CircleTransform()).into(avatar);

        avatar.setImageURI(Uri.parse(AVATAR_URL));


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navigation.findViewById(R.id.navigation_about).setVisibility(View.GONE);

        navigation.findViewById(R.id.navigation_home).performClick();
/*        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setRecyclerAdapter(recyclerView);
        }*/
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);

                    //findViewById(R.id.navigation_home).setVisibility(View.GONE);

                    if(homeFragment==null){
                        homeFragment = new HomeFragment();

                        transaction.add(R.id.content,homeFragment);

                    }else {
                        transaction.show(homeFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    // mTextMessage.setText(R.string.title_dashboard);
                    //findViewById(R.id.navigation_home).setVisibility(View.VISIBLE);
                    if(dashboardFragment==null){
                        dashboardFragment = new DashboardFragment();

                        transaction.add(R.id.content,dashboardFragment);
                    }else {
                        transaction.show(dashboardFragment);
                    }
                    transaction.commit();

                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    if(notificationFragment==null){
                        notificationFragment = new NotificationFragment();

                        transaction.add(R.id.content,notificationFragment);
                    }else {
                        transaction.show(notificationFragment);
                    }
                    transaction.commit();

                    return true;
                case R.id.navigation_about:
                    if(aboutFragment==null){
                        aboutFragment = new AboutFragment();

                        transaction.add(R.id.content,aboutFragment);
                    }else {
                        transaction.show(aboutFragment);
                    }
                    transaction.commit();

                    return true;
            }


            return false;
        }

    };

    private void hideAllFragment(FragmentTransaction transaction) {

        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if(dashboardFragment!=null){
            transaction.hide(dashboardFragment);
        }
        if(notificationFragment!=null){
            transaction.hide(notificationFragment);
        }
        if(aboutFragment!=null){
            transaction.hide(aboutFragment);
        }

    }
/*    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        setRecyclerAdapter(recyclerView);
        recyclerView.scheduleLayoutAnimation();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }*/

    private void initFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                // Snackbar.make(content, "FAB Clicked", Snackbar.LENGTH_SHORT).show();

//                IntentIntegrator integrator =  new IntentIntegrator(MainActivity.this);
//                integrator.setBeepEnabled(false).setOrientationLocked(false);
//                integrator .initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//
//                TastyToast.makeText(getApplicationContext(), result.getContents(), TastyToast.LENGTH_LONG, TastyToast.WARNING);
//
//
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }
//    private void initToolbar() {
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        final ActionBar actionBar = getSupportActionBar();
//
//        if (actionBar != null) {
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(drawerLayout, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

/*    @Override public void onItemClick(View view, ViewModel viewModel) {
       // DetailActivity.navigate(this, view.findViewById(R.id.image), viewModel);
    }*/
}
