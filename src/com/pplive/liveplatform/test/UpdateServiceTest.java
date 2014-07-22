package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.live.UpdateAPI;
import com.pplive.liveplatform.core.api.live.model.Packet;

public class UpdateServiceTest extends AndroidTestCase {
    
    private static final String TAG = UpdateAPI.class.getSimpleName();

    public void testCheckUpdate() {
        Packet packet = UpdateAPI.getInstance().checkUpdate("123", "android_phone", "2.3.5", "2.1.9", "ZTE|Blade|880");
        
        Log.d(TAG, packet.getUrl());
    }
}
