package com.pplive.liveplatform.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.x264.X264SDK;

public class X264EncodeTest extends AndroidTestCase {

    static final String TAG = X264EncodeTest.class.getSimpleName();

    public void testEncode() throws IOException {

        final long handle = X264SDK.x264Create(new X264SDK.EncoderCallback() {

            @Override
            public void invoke(long handle, ByteBuffer buff, int buf_size, long time, int flag) {
                Log.d(TAG, "handle: " + handle + "; buf_size: " + buf_size + "; time: " + time + "; flag: " + flag);
            }
        });

        Log.d(TAG, "handle: " + handle);

        X264SDK.x264SetCconfig(handle, "weight", 320);
        X264SDK.x264SetCconfig(handle, "height", 240);

        X264SDK.x264SetCconfig(handle, "frame_rate", 15);
        X264SDK.x264SetCconfig(handle, "keyframe_interval", 5);

        X264SDK.x264SetCconfig(handle, "bitrate", 500);

        X264SDK.x264SetCconfig(handle, "pic_type", X264SDK.X264_JNI_CSP_NV21);

        Log.d(TAG, "ret: " + X264SDK.x264Open(handle));

        X264SDK.x264GetConfigData(handle);

        byte[] data = new byte[320 * 240 * 3 / 2];
        for (int i = 0; i < 400; ++i) {
            Log.d(TAG, "i: " + i);
            FileInputStream fis = new FileInputStream("/sdcard/buff/YUV" + 0);

            fis.read(data, 0, data.length);

            fis.close();

            ByteBuffer buffer = ByteBuffer.wrap(data);
            X264SDK.x264Encode(handle, buffer, data.length, 70 * i);
        }

    }
}
