package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.TokenService;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;
import com.pplive.liveplatform.core.service.passport.PassportService;
import com.pplive.liveplatform.core.service.passport.model.LoginResult;

public class TokenServiceTest extends AndroidTestCase {

    private static final String TAG = TokenServiceTest.class.getSimpleName();

    public void testGetLiveToken() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                String token = TokenService.getInstance().getLiveToken(Constants.TEST_COTK, program.getId(), program.getOwner());

                Log.d(TAG, "live token: " + token);

                break;
            }
        }
    }

    public void testGetPlayToken() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {
                String token = TokenService.getInstance().getPlayToken(Constants.TEST_COTK, program.getId(), program.getOwner());

                Log.d(TAG, "play token: " + token);

                break;
            }
        }
    }
    
    public void testGetExpireTimeOfToken() throws LiveHttpException {
        
        LoginResult result = PassportService.getInstance().login("xiety0001", "xiety0001");
        
        long expire_time = TokenService.getInstance().getExpireTimeOfToken(result.getToken());
        
        Log.d(TAG, "expire time: " + expire_time);
    }
}
