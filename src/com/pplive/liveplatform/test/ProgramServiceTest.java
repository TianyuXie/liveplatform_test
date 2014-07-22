package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.ProgramAPI;
import com.pplive.liveplatform.core.api.live.model.LiveStatus;
import com.pplive.liveplatform.core.api.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.api.live.model.Program;
import com.pplive.liveplatform.core.api.passport.PassportAPI;
import com.pplive.liveplatform.core.api.passport.model.LoginResult;

public class ProgramServiceTest extends AndroidTestCase {

    private static final String TAG = ProgramServiceTest.class.getSimpleName();
    
    private String mCoToken;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
        
        mCoToken = result.getToken();
    }

    public void testCreateProgram() throws LiveHttpException {
        Program program = new Program("xiety0001", "My Living", System.currentTimeMillis());

        program = ProgramAPI.getInstance().createProgram(mCoToken, program);
        
        Log.d(TAG, "program: " + program);
    }

    public void testGetPrograms() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        for (Program program : programs) {
            Log.d(TAG, program.toString());
        }
    }

    public void testDeleteProgram() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        if (null == programs || programs.size() <= 0) {
            return;
        }

        ProgramAPI.getInstance().deleteProgramById(mCoToken, programs.get(0).getId());
    }

    public void testUpdateProgram() throws LiveHttpException {
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        if (null == programs || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.NOT_START == program.getLiveStatus()) {
                program.setTitle("I'm a hero.");
                
                ProgramAPI.getInstance().updateProgram(mCoToken, program);
            }
        }
    }
    
    public void testGetLiveStatus() throws LiveHttpException {
        
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");
        
        if (null == programs || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            LiveStatus liveStatus = ProgramAPI.getInstance().getLiveStatus(program.getId());
            
            Log.d(TAG, "live status: " + liveStatus.getStatus());
        }
    }

}
