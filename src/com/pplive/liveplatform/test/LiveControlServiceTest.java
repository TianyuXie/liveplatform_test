package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.LiveControlAPI;
import com.pplive.liveplatform.core.api.live.ProgramAPI;
import com.pplive.liveplatform.core.api.live.model.LiveAlive;
import com.pplive.liveplatform.core.api.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.api.live.model.Program;
import com.pplive.liveplatform.core.api.passport.PassportAPI;
import com.pplive.liveplatform.core.api.passport.model.LoginResult;

public class LiveControlServiceTest extends AndroidTestCase {

    private static final String TAG = LiveControlServiceTest.class.getSimpleName();
    
    public void testUpdateLiveStatus() throws LiveHttpException {
        
        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
        
        String coToken = result.getToken();
        
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");
        
        for (Program program : programs) {
            if (LiveStatusEnum.NOT_START == program.getLiveStatus()) {
                LiveControlAPI.getInstance().updateLiveStatusByCoToken(coToken, program, "xiety0001");
                LiveControlAPI.getInstance().updateLiveStatusByCoToken(coToken, program, "xiety0001");
                LiveControlAPI.getInstance().updateLiveStatusByCoToken(coToken, program, "xiety0001");
            }
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                LiveControlAPI.getInstance().updateLiveStatusByCoToken(coToken, program, "xiety0001");
                LiveControlAPI.getInstance().updateLiveStatusByCoToken(coToken, program, "xiety0001");
            }
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.PREVIEW == program.getLiveStatus()) {
                LiveControlAPI.getInstance().updateLiveStatusByCoToken(coToken, program, "xiety0001");
            }
        }
    }
    
    public void testKeepLiveAlive() throws LiveHttpException {
    	
    	List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");
    	
    	for (Program program : programs) {
    		if (LiveStatusEnum.LIVING == program.getLiveStatus()) {
    			LiveAlive liveAlive = LiveControlAPI.getInstance().keepLiveAlive(Constants.TEST_COTK, program.getId());
    			
    			Log.d(TAG, "pid: " + liveAlive.getProgramId() + "; delay: " + liveAlive.getDelayInSeconds());
    			
    			break;
    		}
    	}
    }
}
