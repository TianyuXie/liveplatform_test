package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.Constants;
import com.pplive.liveplatform.core.service.live.MediaService;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;
import com.pplive.liveplatform.core.service.live.model.Push;
import com.pplive.liveplatform.core.service.live.model.Watch;

public class MediaServiceTest extends AndroidTestCase {

    private static final String TAG = MediaServiceTest.class.getSimpleName();

    public void testGetPush() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                Push push = MediaService.getInstance().getPush(Constants.TEST_COTK, program.getId(), "xiety0001");

                for (String url : push.getPushStringList()) {
                    Log.d(TAG, "push url: " + url);
                }
                
                break;
            }
        }
    }

    public void testGetPreviewWatchList() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

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

    public void testGetPlayWatchList() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {

                List<Watch> watchs = MediaService.getInstance().getPlayWatchList(Constants.TEST_COTK, program.getId(), "xiety0001");
                
                for (Watch watch : watchs) {
                    for (String url : watch.getWatchStringList()) {
                        Log.d(TAG, "sdk play url: " + url);
                    }
                }
                
                break;
            }
        }
    }
}
