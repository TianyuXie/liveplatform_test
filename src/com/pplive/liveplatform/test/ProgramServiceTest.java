package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.Constants;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.LiveStatusEnum;
import com.pplive.liveplatform.core.service.live.model.Program;

public class ProgramServiceTest extends AndroidTestCase {

    private static final String TAG = ProgramServiceTest.class.getSimpleName();

    public void testCreateProgram() {
        Program program = new Program("xiety0001", "My Living", System.currentTimeMillis());

        program = ProgramService.getInstance().createProgram(Constants.TEST_COTK, program);
        
        Log.d(TAG, "program: " + program);
    }

    public void testGetPrograms() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

        for (Program program : programs) {
            Log.d(TAG, program.toString());
        }
    }

    public void testDeleteProgram() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

        if (programs == null || programs.size() <= 0) {
            return;
        }

        ProgramService.getInstance().deleteProgramById(Constants.TEST_COTK, programs.get(0).getId());
    }

    public void testUpdateProgram() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

        if (programs == null || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            if (LiveStatusEnum.NOT_START == program.getLiveStatus()) {
                program.setTitle("I'm a hero.");
                
                ProgramService.getInstance().updateProgram(Constants.TEST_COTK, program);
            }
        }
    }

}
