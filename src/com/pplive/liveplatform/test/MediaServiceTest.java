package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.MediaService;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;
import com.pplive.liveplatform.core.service.live.model.Push;
import com.pplive.liveplatform.core.service.live.model.Watch;
import com.pplive.liveplatform.core.service.live.model.WatchList;
import com.pplive.liveplatform.core.service.passport.PassportService;
import com.pplive.liveplatform.core.service.passport.model.LoginResult;

public class MediaServiceTest extends AndroidTestCase {

    private static final String TAG = MediaServiceTest.class.getSimpleName();

    public void testGetPush() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                Push push = MediaService.getInstance().getPush(Constants.TEST_COTK, program.getId(), "xiety0001");

                for (String url : push.getPushUrlList()) {
                    Log.d(TAG, "push url: " + url);
                }
                
                break;
            }
        }
    }

    public void testGetPreviewWatchList() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.PREVIEW == program.getLiveStatus()) {
                List<Watch> watchs = MediaService.getInstance().getPreviewWatchList(Constants.TEST_COTK, program.getId(), "xiety0001");

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
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {

                List<Watch> watchs = MediaService.getInstance().getPlayWatchListV1(Constants.TEST_COTK, program.getId(), "xiety0001");
                
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
        
        LoginResult result = PassportService.getInstance().login("xiety0001", "xiety0001");
        
        String coToken = result.getToken();
        
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");
        
        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {

                WatchList watchs = MediaService.getInstance().getPlayWatchListV2(coToken, program.getId(), "xiety0001");
                
                watchs.getRecommendedWatch();
                
                break;
            }
        }
    }
}
