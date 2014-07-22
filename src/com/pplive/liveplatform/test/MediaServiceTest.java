package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.MediaAPI;
import com.pplive.liveplatform.core.api.live.ProgramAPI;
import com.pplive.liveplatform.core.api.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.api.live.model.Program;
import com.pplive.liveplatform.core.api.live.model.Push;
import com.pplive.liveplatform.core.api.live.model.Watch;
import com.pplive.liveplatform.core.api.live.model.WatchList;
import com.pplive.liveplatform.core.api.passport.PassportAPI;
import com.pplive.liveplatform.core.api.passport.model.LoginResult;

public class MediaServiceTest extends AndroidTestCase {

    private static final String TAG = MediaServiceTest.class.getSimpleName();

    public void testGetPush() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                Push push = MediaAPI.getInstance().getPush(Constants.TEST_COTK, program.getId(), "xiety0001");

                for (String url : push.getPushUrlList()) {
                    Log.d(TAG, "push url: " + url);
                }
                
                break;
            }
        }
    }

    public void testGetPreviewWatchList() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.PREVIEW == program.getLiveStatus()) {
                List<Watch> watchs = MediaAPI.getInstance().getPreviewWatchList(Constants.TEST_COTK, program.getId(), "xiety0001");

                for (Watch watch : watchs) {
                    for (String url : watch.getWatchStringList()) {
                        Log.d(TAG, "preview url: " + url);
                    }
                }

                break;
            }
        }
    }

    public void testGetPlayWatchList() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {

                List<Watch> watchs = MediaAPI.getInstance().getPlayWatchListV1(Constants.TEST_COTK, program.getId(), "xiety0001");
                
                for (Watch watch : watchs) {
                    for (String url : watch.getWatchStringList()) {
                        Log.d(TAG, "sdk play url: " + url);
                    }
                }
                
                break;
            }
        }
    }
    
    public void testGetRecommendWathList() throws LiveHttpException {
        
        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
        
        String coToken = result.getToken();
        
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");
        
        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {

                WatchList watchs = MediaAPI.getInstance().getPlayWatchListV3(coToken, program.getId(), "xiety0001");
                
                watchs.getRecommendedWatch();
                
                break;
            }
        }
    }
}
