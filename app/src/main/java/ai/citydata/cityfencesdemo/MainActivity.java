package ai.citydata.cityfencesdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ai.citydata.cityfences.CityFences;
import ai.citydata.cityfences.permissions.PermissionCallBack;

public class MainActivity extends AppCompatActivity {

    private CityFences sdkInstance = null;
    protected Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mContext = this;

        /** [SDK] THE BELOW LINE MUST TO BE ADDED **/
        initSDK();
    }

    /**
     * Method in charge to initialize the SDK and request for runtime permissions
     */
    private void initSDK() {

        /** CityFences.getInstance parameters
         * Param1: context
         * Param2: customer's API KEY
         * Param3: notification icon
         * Param4: background enabled (false for foreground only)
         */
        sdkInstance = CityFences.getInstance( this, "<YOUR API KEY>", R.drawable.citydata_notification_icon, true);

        if(sdkInstance != null) {
            sdkInstance.requestPermissions(this, new PermissionCallBack() {
                @Override
                public void onResult(boolean b) {
                    launchApp();
                }
            });

            //Example demonstrating how to report an event
            sdkInstance.initCustomEvent();
            sdkInstance.addEventItem("launch","SDK successfully initialized");
            sdkInstance.reportCustomEvent();

        } else {
            Log.d("MainActivity", "sdk instance is null");
        }
    }

    /**
     * Method in charge to launch app and SDK service
     */
    private void launchApp() {
        /** YOUR CODE HERE **/

        /** [SDK] THE BELOW LINE MUST TO BE ADDED **/
        sdkInstance.start(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /** [SDK] THE BELOW LINE MUST TO BE ADDED **/
        if(sdkInstance != null) sdkInstance.onStart(this);
    }

    @Override
    protected void onStop() {
        /** [SDK] THE BELOW LINE MUST TO BE ADDED **/
        if(sdkInstance != null) sdkInstance.onStop(this);

        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(sdkInstance != null) sdkInstance.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
