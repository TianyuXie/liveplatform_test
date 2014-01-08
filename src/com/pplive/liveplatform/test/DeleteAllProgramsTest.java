package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.Program;
import com.pplive.liveplatform.core.service.passport.PassportService;
import com.pplive.liveplatform.core.service.passport.model.LoginResult;

public class DeleteAllProgramsTest extends AndroidTestCase {

    
    public void testDeleteAllProgram() throws LiveHttpException {
        LoginResult result = PassportService.getInstance().login("xiety0001", "xiety0001");
        
        String coToken = result.getToken();
        
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(Constants.TEST_COTK, "xiety0001");

        if (programs == null || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            ProgramService.getInstance().deleteProgramById(coToken, program.getId());
        }
    }
}
