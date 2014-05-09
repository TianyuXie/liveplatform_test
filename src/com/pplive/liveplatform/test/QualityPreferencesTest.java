package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.network.QualityPreferences;

public class QualityPreferencesTest extends AndroidTestCase {

    public void testUpdate() {
        QualityPreferences.getInstance(getContext()).setIP("192.168.27.121");
        QualityPreferences.getInstance(getContext()).reset();
    }

}
