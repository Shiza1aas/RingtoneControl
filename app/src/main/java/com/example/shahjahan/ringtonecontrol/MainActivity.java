package com.example.shahjahan.ringtonecontrol;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments, using FragmentTabHost.
 */
public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        toolbar goes here
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(" Ringtone Control");

        setSupportActionBar(toolbar);

        //the fragment section


        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("STATUS"),
                Status.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("ALLOW"),
                AllowNumber.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("BLOCK"),
                AllowNumber.class, null);

    }
}
