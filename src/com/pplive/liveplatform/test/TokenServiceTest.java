package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.ProgramAPI;
import com.pplive.liveplatform.core.api.live.TokenAPI;
import com.pplive.liveplatform.core.api.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.api.live.model.Program;
import com.pplive.liveplatform.core.api.passport.PassportAPI;
import com.pplive.liveplatform.core.api.passport.model.LoginResult;

public class TokenServiceTest extends AndroidTestCase {

    private static final String TAG = TokenServiceTest.class.getSimpleName();

    public void testGetLiveToken() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                String token = TokenAPI.getInstance().getLiveToken(Constants.TEST_COTK, program.getId(), program.getOwner());

                Log.d(TAG, "live token: " + token);

                break;
            }
        }
    }

    public void testGetPlayToken() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            if (LiveStatusEnum.LIVING == program.getLiveStatus()) {
                String token = TokenAPI.getInstance().getPlayToken(Constants.TEST_COTK, program.getId(), program.getOwner());

                Log.d(TAG, "play token: " + token);

                break;
            }
        }
    }
    
    public void testGetExpireTimeOfToken() throws LiveHttpException {
        
        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
        
        long expire_time = TokenAPI.getInstance().getExpireTimeOfToken(result.getToken());
        
        Log.d(TAG, "expire time: " + expire_time);
    }
}
