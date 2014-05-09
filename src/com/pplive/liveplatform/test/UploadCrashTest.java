package com.pplive.liveplatform.test;

import android.os.Build;
import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.dac.info.AppInfo;
import com.pplive.liveplatform.core.dac.info.DeviceInfo;
import com.pplive.liveplatform.core.service.crash.LogService;

public class UploadCrashTest extends AndroidTestCase {

    static final String TAG = UpdateServiceTest.class.getSimpleName();

    public void testLogCrash() {
        String uploadUrl = LogService.getInstance().uploadCrash(DeviceInfo.getDeviceId(), Build.BRAND, Build.VERSION.RELEASE,
                AppInfo.getVersionName(), AppInfo.getChannel(), true);

        Log.d(TAG, "upload url: " + uploadUrl);
    }
}
