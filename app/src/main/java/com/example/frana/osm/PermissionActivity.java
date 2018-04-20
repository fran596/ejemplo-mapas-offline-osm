package com.example.frana.osm;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.osmdroid.config.Configuration;

/**
 * @author Luca Campana
 * This activity come before the MainActivity where the map is displayed
 * Here is where we check the permission, if we cant' read the external storage
 * we will not be able to read and show offline tiles
 * If the device is api >= 23 it would require user manual permission
 * The button will be enabled only when the permission is activated
 */
public class PermissionActivity extends Activity {

    private final static int PERMISSION_CODE = 111;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMapActivity();
            }
        });
        button.setEnabled(askPermission());
    }

    /**
     * This check if permission is granted for this application, if this is not and we are running the application
     * on a device that is api >= 23 this will trigger a user request where we caught the result in
     * onRequestPermissionResult
     * @return
     */
    private boolean askPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
            return false;
        }
        else return true;
    }

    /**
     * This is the callback response from calling ActivityCompat.requestPermission,
     * if the user discard the permission the application will be closed
     * @param requestCode   It should be PERMISSION_CODE
     * @param permissions   The permission
     * @param grantResults  The result that we check
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    button.setEnabled(true);
                }
                else {
                    finish();
                }
            }
        }
    }

    /**
     * Go to the main activity
     */
    public void goToMapActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}