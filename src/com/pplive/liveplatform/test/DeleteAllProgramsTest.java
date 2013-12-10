package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.Program;

public class DeleteAllProgramsTest extends AndroidTestCase {

    
    public void testDeleteAllProgram() {
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner("xiety0001");

        if (programs == null || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            ProgramService.getInstance().deleteProgramById(Constants.TEST_COTK, program.getId());
        }
    }
}
