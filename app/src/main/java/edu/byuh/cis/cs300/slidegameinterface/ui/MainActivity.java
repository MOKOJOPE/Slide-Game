package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

//Run it
/**
 * Main activity class that extends AppCompatActivity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Instance of MomoView class.
     */
    private MomoView momo;

    /**
     * Called when the activity is created.
     *
     * @param b Bundle object containing saved instance state.
     */
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        EdgeToEdge.enable(this);
        momo = new MomoView(this);
        setContentView(momo);
    }
}