package com.pplive.liveplatform.test;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.os.Build;
import android.test.AndroidTestCase;
import android.util.Log;

public class MediaCodecTest extends AndroidTestCase {
    
    private static final String TAG = MediaCodecTest.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void testCreateMediaCodec() {
        String mime = "video/avc";
        
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; ++i) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);

            String[] types = info.getSupportedTypes();

            for (int j = 0; j < types.length; ++j) {

                if (info.isEncoder() && types[j].equals(mime)) {
                    Log.d(TAG, info.getName());
                    CodecCapabilities caps = info.getCapabilitiesForType(types[j]);

                    int[] colorFormats = caps.colorFormats;
                    int colorFormat = colorFormats[0];
                    for (int k = 0; k < colorFormats.length; ++k) {
                        Log.d(TAG, "color: " + colorFormats[k]);
                        if (MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar == colorFormats[k]) {
                            colorFormat = MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar;
                            
                            
                            Log.d(TAG, "colorFormat: " + colorFormats[k]);
                        }
                    }
                    
                    MediaCodecInfo.CodecProfileLevel[] profiles = caps.profileLevels;
                    for (int k = 0; k < profiles.length; ++k) {
                        Log.d(TAG, "profile: " + profiles[k].profile + "; level: " + profiles[k].level);
                    }

                }
            }
        }
    }
    
    
    public void testCreateCamera() {
        int numberOfCameras = Camera.getNumberOfCameras();

        for (int i = 0; i < numberOfCameras; ++i) {
            Camera camera = Camera.open(i);
            Parameters params = camera.getParameters();
            
            for (int format : params.getSupportedPreviewFormats()) {
                Log.d(TAG, "format: " + format);
            }
            
            
            camera.release();
        }
        
    }
}
