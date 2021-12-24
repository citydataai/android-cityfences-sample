package ai.citydata.cityfencesdemo;

import android.content.Context;
import android.os.Bundle;

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
        sdkInstance = CityFences.getInstance( this, "YOUR API KEY HERE", R.drawable.citydata_notification_icon);

        if(sdkInstance != null) {
            sdkInstance.requestPermissions(this, new PermissionCallBack() {
                @Override
                public void onResult(boolean b) {
                    launchApp();
                }
            });
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
}
