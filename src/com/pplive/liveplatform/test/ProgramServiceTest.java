package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.service.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.LiveStatus;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;
import com.pplive.liveplatform.core.service.passport.PassportService;
import com.pplive.liveplatform.core.service.passport.model.LoginResult;

public class ProgramServiceTest extends AndroidTestCase {

    private static final String TAG = ProgramServiceTest.class.getSimpleName();
    
    private String mCoToken;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        LoginResult result = PassportService.getInstance().login("xiety0001", "xiety0001");
        
        mCoToken = result.getToken();
    }

    public void testCreateProgram() throws LiveHttpException {
        Program program = new Program("xiety0001", "My Living", System.currentTimeMillis());

        program = ProgramService.getInstance().createProgram(mCoToken, program);
        
        Log.d(TAG, "program: " + program);
    }

    public void testGetPrograms() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            Log.d(TAG, program.toString());
        }
    }

    public void testDeleteProgram() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        if (null == programs || programs.size() <= 0) {
            return;
        }

        ProgramService.getInstance().deleteProgramById(mCoToken, programs.get(0).getId());
    }

    public void testUpdateProgram() throws LiveHttpException {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        if (null == programs || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.NOT_START == program.getLiveStatus()) {
                program.setTitle("I'm a hero.");
                
                ProgramService.getInstance().updateProgram(mCoToken, program);
            }
        }
    }
    
    public void testGetLiveStatus() throws LiveHttpException {
        
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");
        
        if (null == programs || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            LiveStatus liveStatus = ProgramService.getInstance().getLiveStatus(program.getId());
            
            Log.d(TAG, "live status: " + liveStatus.getStatus());
        }
    }

}
