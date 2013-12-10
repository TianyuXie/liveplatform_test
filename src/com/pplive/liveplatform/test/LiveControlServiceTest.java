package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.service.live.LiveControlService;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;

public class LiveControlServiceTest extends AndroidTestCase {

    @SuppressWarnings("unused")
    private static final String TAG = LiveControlServiceTest.class.getSimpleName();
    
    public void testUpdateLiveStatus() {
        
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
}
