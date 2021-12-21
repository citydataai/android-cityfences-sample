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

        initSDK();
    }

    /**
     *
     */
    private void initSDK() {
        sdkInstance = CityFences.getInstance( this, "<your API key here>", R.drawable.citydata_notification_icon);

        if(sdkInstance != null) {
            sdkInstance.requestPermissions(this, new PermissionCallBack() {
                @Override
                public void onResult(boolean b) {
                    launch();
                }
            });
        }
    }

    /**
     *
     */
    private void launch() {
        sdkInstance.start(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sdkInstance != null) sdkInstance.onStart(this);
    }

    @Override
    protected void onStop() {
        if(sdkInstance != null) sdkInstance.onStop(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
