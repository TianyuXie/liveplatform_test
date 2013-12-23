package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.LiveControlService;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.LiveAlive;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;

public class LiveControlServiceTest extends AndroidTestCase {

    private static final String TAG = LiveControlServiceTest.class.getSimpleName();
    
    public void testUpdateLiveStatus() throws LiveHttpException {
        
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");
        
        for (Program program : programs) {
            if (LiveStatusEnum.NOT_START == program.getLiveStatus()) {
                LiveControlService.getInstance().updateLiveStatusByCoToken(Constants.TEST_COTK, program.getId(), LiveStatusEnum.INIT, "xiety0001");
                LiveControlService.getInstance().updateLiveStatusByCoToken(Constants.TEST_COTK, program.getId(), LiveStatusEnum.PREVIEW, "xiety0001");
                LiveControlService.getInstance().updateLiveStatusByCoToken(Constants.TEST_COTK, program.getId(), LiveStatusEnum.LIVING, "xiety0001");
            }
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.INIT == program.getLiveStatus()) {
                LiveControlService.getInstance().updateLiveStatusByCoToken(Constants.TEST_COTK, program.getId(), LiveStatusEnum.PREVIEW, "xiety0001");
                LiveControlService.getInstance().updateLiveStatusByCoToken(Constants.TEST_COTK, program.getId(), LiveStatusEnum.LIVING, "xiety0001");
            }
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.PREVIEW == program.getLiveStatus()) {
                LiveControlService.getInstance().updateLiveStatusByCoToken(Constants.TEST_COTK, program.getId(), LiveStatusEnum.LIVING, "xiety0001");
            }
        }
    }
    
    public void testKeepLiveAlive() throws LiveHttpException {
    	
    	List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");
    	
    	for (Program program : programs) {
    		if (LiveStatusEnum.LIVING == program.getLiveStatus()) {
    			LiveAlive liveAlive = LiveControlService.getInstance().keepLiveAlive(Constants.TEST_COTK, program.getId());
    			
    			Log.d(TAG, "pid: " + liveAlive.getProgramId() + "; delay: " + liveAlive.getDelayInSeconds());
    			
    			break;
    		}
    	}
    }
}
