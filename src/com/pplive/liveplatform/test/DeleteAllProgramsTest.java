package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.service.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.ProgramService;
import com.pplive.liveplatform.core.service.live.model.Program;
import com.pplive.liveplatform.core.service.passport.PassportService;
import com.pplive.liveplatform.core.service.passport.model.LoginResult;

public class DeleteAllProgramsTest extends AndroidTestCase {

    
    public void testDeleteAllProgram() throws LiveHttpException {
        LoginResult result = PassportService.getInstance().login("stefanielj", "510215");
        
        String coToken = result.getToken();
        
        List<Program> programs = ProgramService.getInstance().getProgramsByOwner(coToken, "stefanielj");

        if (programs == null || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            ProgramService.getInstance().deleteProgramById(coToken, program.getId());
        }
    }
}
