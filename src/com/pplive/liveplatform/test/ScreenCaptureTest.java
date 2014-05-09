package com.pplive.liveplatform.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.test.AndroidTestCase;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ScreenCaptureTest extends AndroidTestCase {

    static final String TAG = ScreenCaptureTest.class.getSimpleName();

    public void testScreenCapture() throws IOException, InterruptedException {

        Process process = new ProcessBuilder(new String[] { "su" }).redirectErrorStream(true).start();

        DataOutputStream dos = new DataOutputStream(process.getOutputStream());
        DataInputStream dis = new DataInputStream(process.getInputStream());

        dos.write("cat /dev/graphics/fb0".getBytes());
        dos.write("\n".getBytes());
        dos.flush();

        DisplayMetrics metrics = new DisplayMetrics();

        WindowManager wm = (WindowManager) getContext().getSystemService(Service.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int pixelFormat = display.getPixelFormat();
        PixelFormat localPixelFormat = new PixelFormat();
        PixelFormat.getPixelFormatInfo(pixelFormat, localPixelFormat);
        int deepth = localPixelFormat.bytesPerPixel;

        long start = System.currentTimeMillis();

        byte[] piex = new byte[width * height * deepth];

        Log.d(TAG, "Pixel Format: " + pixelFormat);

        Log.d(TAG, "deepth: " + deepth);

        Log.d(TAG, "width: " + width + "; height: " + height);

        dis.readFully(piex, 0, piex.length);

        int[] colors = new int[width * height];

        // 将rgb转为色值
        for (int m = 0; m < colors.length; m++) {
            int r = (piex[m * 4 + 2] & 0xFF);
            int g = (piex[m * 4 + 1] & 0xFF);
            int b = (piex[m * 4 + 0] & 0xFF);
            int a = (piex[m * 4 + 3] & 0xFF);
            colors[m] = (a << 24) + (r << 16) + (g << 8) + b;
        }

        Log.d(TAG, "duration: " + (System.currentTimeMillis() - start));

        Bitmap bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888);

        FileOutputStream os = new FileOutputStream("/sdcard/screen.png");

        bitmap.compress(Bitmap.CompressFormat.PNG, 90, os);

        os.close();

        dis.close();
    }
}
