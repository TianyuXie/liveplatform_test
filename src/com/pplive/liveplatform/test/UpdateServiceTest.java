package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.service.live.UpdateService;
import com.pplive.liveplatform.core.service.live.model.Packet;

public class UpdateServiceTest extends AndroidTestCase {
    
    private static final String TAG = UpdateService.class.getSimpleName();

    public void testCheckUpdate() {
        Packet packet = UpdateService.getInstance().checkUpdate("123", "android_phone", "2.3.5", "2.1.9", "ZTE|Blade|880");
        
        Log.d(TAG, packet.getUrl());
    }
}
