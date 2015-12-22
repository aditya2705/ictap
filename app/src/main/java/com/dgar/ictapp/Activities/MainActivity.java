package com.dgar.ictapp.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.dgar.ictapp.Fragments.BlankFragment;
import com.dgar.ictapp.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends AppCompatActivity {

    private Drawer result = null;
    private FloatingActionButton fab;
    private float defaultx, defaulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Account header for the Google Material Drawer
        //Responsive to multiple accounts
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(true)
                .withTextColorRes(R.color.md_white_1000)
                .addProfiles(
                        new ProfileDrawerItem().withName("Dummy Name1")
                                .withTextColorRes(R.color.md_grey_800)
                                .withTextColor(Color.BLACK)
                                .withEmail("dummy_email_id1@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.profile)),

                        new ProfileDrawerItem().withName("Dummy Name2")
                                .withTextColorRes(R.color.md_grey_800)
                                .withTextColor(Color.BLACK)
                                .withEmail("dummy_email_id2@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.profile))


                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //Create the drawer
        result = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withRootView(R.id.drawer_container)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Primary Item 1").withIcon(FontAwesome.Icon.faw_android),
                        new PrimaryDrawerItem().withName("Primary Item 2").withIcon(FontAwesome.Icon.faw_android),
                        new PrimaryDrawerItem().withName("Primary Item 3").withIcon(FontAwesome.Icon.faw_android),
                        new SectionDrawerItem().withName("SECTION I"),
                        new PrimaryDrawerItem().withName("Primary Item 4").withIcon(FontAwesome.Icon.faw_android),
                        new PrimaryDrawerItem().withName("Primary Item 5").withIcon(FontAwesome.Icon.faw_android)


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem drawerItem) {

                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            String name = ((Nameable) drawerItem).getName().getText(MainActivity.this);
                            //getSupportActionBar().setTitle(name);
                            Fragment fragment;
                            switch (i) {


                                default:
                                    fragment = new BlankFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                    break;

                            }
                        }

                        return false;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        fab.setEnabled(false);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        fab.setEnabled(true);
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                        fab.setScaleX((1f-slideOffset)*defaultx);
                        fab.setScaleY((1f-slideOffset)*defaulty);

                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();



        fab = (FloatingActionButton) findViewById(R.id.fab);
        defaultx = fab.getScaleX();
        defaulty = fab.getScaleY();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(findViewById(R.id.fragment_container), "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
