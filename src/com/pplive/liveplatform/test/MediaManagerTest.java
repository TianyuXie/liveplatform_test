package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.ui.record.MediaManager;

public class MediaManagerTest extends AndroidTestCase {

    public void testCreateCodec() {
        MediaManager.getInstance().getSupportedEncodingVideoFormat(MediaManager.MIME_TYPE_VIDEO_AVC, null);
    }
}
