package com.example.wgl.test;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NavActivity extends AppCompatActivity {

    //private TextView mTextMessage;

    //private FrameLayout frameLayout;

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationFragment notificationFragment;

    private AboutFragment aboutFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        //mTextMessage = (TextView) findViewById(R.id.message);

        //frameLayout = (FrameLayout) findViewById(R.id.content);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navigation.findViewById(R.id.navigation_about).setVisibility(View.GONE);

        navigation.findViewById(R.id.navigation_home).performClick();
    }

}
