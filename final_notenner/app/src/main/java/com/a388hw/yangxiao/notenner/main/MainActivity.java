package com.a388hw.yangxiao.notenner.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.a388hw.yangxiao.notenner.LoginActivity;
import com.a388hw.yangxiao.notenner.R;
import com.a388hw.yangxiao.notenner.main.fragments.AddItemFragment;
import com.a388hw.yangxiao.notenner.main.fragments.EventDisplayFragment;
import com.a388hw.yangxiao.notenner.main.fragments.EventsListFragment;
import com.a388hw.yangxiao.notenner.user.Event;
import com.a388hw.yangxiao.notenner.user.User;
import com.a388hw.yangxiao.notenner.util.UserHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.EventListener;

import static com.a388hw.yangxiao.notenner.util.util.showSnackbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserHolder, EventsListFragment.OnListFragmentInteractionListener {

    public static final String TAG = "MainActivity";
    public static final String LOGOUT_TAG = "ActLogout";

    User user;
    String username;

    public static final String USERS_DIRECTORY = "/users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        switchTo(EventsListFragment.TAG, null, true);
    }

    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.mainViewTitle);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                        .setAction("Action", null).show();
                switchTo(AddItemFragment.TAG, null, true);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initData() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        username = firebaseUser.getEmail();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        user = new User(username,
                database.getReference(USERS_DIRECTORY).child(firebaseUser.getUid()));

        showSnackbar(getWindow().getDecorView().getRootView(), getApplicationContext(),
                "You are signed in as: " + firebaseUser.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_signout) {
            signOut();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean switchTo(String tag, Event event, boolean toBackStack) {

        if (tag.equals(LOGOUT_TAG)) {
            signOut();
            return true;
        }

        Fragment fragment = getFragmentFromTag(tag, event);
        if (fragment == null) {
            return false;
        }

        FragmentManager fagManager = getSupportFragmentManager();
        FragmentTransaction transaction = fagManager.beginTransaction();
        transaction.replace(R.id.mainContent, fragment, tag);
        if (toBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();

        return true;
    }

    private Fragment getFragmentFromTag(String tag, Event event) {
        switch (tag) {
            case AddItemFragment.TAG:
                if (event != null) {
                    return AddItemFragment.newInstance(event);
                } else {
                    return new AddItemFragment();
                }
            case EventsListFragment.TAG:
                return EventsListFragment.newInstance("1", "2");
            case EventDisplayFragment.TAG:
                return EventDisplayFragment.newInstance(event);
        }
        return null;
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void onListFragmentInteraction(Event event) {
        switchTo(EventDisplayFragment.TAG, event, true);
    }
}
