package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.record.MediaManager;
import com.pplive.liveplatform.core.record.Quality;

public class MediaManagerTest extends AndroidTestCase {

    public void testCreateCodec() {
        MediaManager.getInstance().getSupportedEncodingVideoFormat(MediaManager.MIME_TYPE_VIDEO_AVC, null, Quality.Normal);
    }
}
