package com.attentive.androidwrapper;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.LinearLayout;

import com.attentive.androidsdk.AttentiveConfig;
import com.attentive.androidsdk.AttentiveEventTracker;
import com.attentive.androidsdk.creatives.Creative;


public class AttentiveAndroidWrapper {
    private static String LOG_TAG = "AttentiveAndroidWrapper";

    private AttentiveConfig attentiveConfig;
    private Creative creative;

    private Activity unityActivity;
    private LinearLayout webLayout;


    public void init(String domain, String mode, Activity activity) {
        Log.i(
                LOG_TAG,
                String.format("Initializing the Android Wrapper with domain %s and name %s ", domain, mode));
        attentiveConfig = new AttentiveConfig(
                domain,
                AttentiveConfig.Mode.valueOf(mode.toUpperCase()), activity.getApplicationContext());
        this.unityActivity = activity;
        AttentiveEventTracker.getInstance().initialize(attentiveConfig);
    }

    public void trigger() {
        if(unityActivity != null) {
            unityActivity.runOnUiThread(() -> {
                Log.i(LOG_TAG, "Triggering the creative!");

                if (webLayout == null) {
                    webLayout = new LinearLayout(unityActivity);
                    webLayout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    unityActivity.addContentView(webLayout, layoutParams);
                }

                if (creative == null) {
                    creative = new Creative(attentiveConfig, webLayout);
                }

                creative.trigger();
            });
        } else {
            Log.e(LOG_TAG, "Couldn't find current activity, cannot trigger the creative.");
        }
    }

    public void clearCookies() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
    }
}
