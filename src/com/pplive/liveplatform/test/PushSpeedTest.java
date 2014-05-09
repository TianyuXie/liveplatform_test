package com.pplive.liveplatform.test;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.service.live.MediaService;

public class PushSpeedTest extends AndroidTestCase {

    static final String TAG = PushSpeedTest.class.getSimpleName();

    static final int[] data = { 128 * 1024, 256 * 1024, 512 * 1024, 1024 * 1024, 2 * 1024 * 1024 };

    static final int count = 5;

    byte[] buff = null;

    ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        parameters.add(new NameValuePair() {

            @Override
            public String getValue() {
                return new String(buff);
            }

            @Override
            public String getName() {
                return "data";
            }
        });
    }

    /*public void testPushSpeed() throws ClientProtocolException, IOException {

        for (int numOfBytes : data) {
            Log.d(TAG, "Num Of Bytes: " + numOfBytes);
            buff = new byte[numOfBytes];

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
            HttpPost post = new HttpPost("http://172.16.205.48:80/net-speed");
            post.setEntity(entity);
            HttpClient client = new DefaultHttpClient();

            ArrayList<Double> duration_samples = new ArrayList<Double>(count);
            ArrayList<Double> speed_samples = new ArrayList<Double>(count);

            for (int i = 0; i < count; ++i) {
                //                Log.d(TAG, "Post Test " + i);
                long start = System.currentTimeMillis();
                HttpResponse resp = client.execute(post);

                resp.getStatusLine().getStatusCode();

                InputStream is = resp.getEntity().getContent();
                InputStreamReader r = new InputStreamReader(is);

                char[] buff = new char[1024];
                int readBytes = -1;
                StringBuilder sb = new StringBuilder();
                while ((readBytes = r.read(buff)) > 0) {
                    sb.append(buff, 0, readBytes);
                }

                String[] splits = sb.toString().split("\n");

                long end = System.currentTimeMillis();

                double duration = (double) (end - start) / 1000f;

                //                Log.d(TAG, "STATUS: " + resp.getStatusLine().getStatusCode());
                //                Log.d(TAG, "Duration(second): " + (duration));
                //                Log.d(TAG, "Content Length: " + resp.getEntity().getContentLength());

                //                Log.d(TAG, "Speed(Local): " + ((float) numOfBytes / duration));
                //                Log.d(TAG, "Speed(Server): " + splits[0]);
                //                Log.d(TAG, "Client IP: " + splits[1]);

                duration_samples.add(duration);
                speed_samples.add(Double.valueOf(splits[0]));
            }

            double avg_duration = getAverage(duration_samples);
            double sd_duration = getStandardDevition(avg_duration, duration_samples);

            Log.d(TAG, "Duration(Average): " + avg_duration);
            //            Log.d(TAG, "Duration(Standard Devition): " + sd_duration);

            double avg_speed = getAverage(speed_samples);
            double sd_speed = getStandardDevition(avg_speed, speed_samples);

            Log.d(TAG, "Speed(Average): " + avg_speed);
            //            Log.d(TAG, "Speed(Standard Devition): " + sd_speed);
        }

    }

    private double getAverage(ArrayList<Double> samples) {

        float sum = 0;
        for (double sample : samples) {
            sum += sample;
        }

        return sum / samples.size();
    }

    private double getStandardDevition(double average, ArrayList<Double> samples) {
        float sum = 0;
        for (double sample : samples) {
            sum += Math.pow(sample - average, 2);
        }

        return sum / (samples.size() - 1);
    }*/

    public void testGetClientIP() {

        Log.d(TAG, "IP Address: " + MediaService.getInstance().getClientIPAddress());
        
        Log.d(TAG, "Speed: " + MediaService.getInstance().getAvgNetSpeed(256 * 1024, 5));
    }
}
