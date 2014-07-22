package com.pplive.liveplatform.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.ProgramAPI;
import com.pplive.liveplatform.core.api.live.model.Program;
import com.pplive.liveplatform.core.api.passport.PassportAPI;
import com.pplive.liveplatform.core.api.passport.model.LoginResult;

public class DeleteAllProgramsTest extends AndroidTestCase {

    
    public void testDeleteAllProgram() throws LiveHttpException {
        LoginResult result = PassportAPI.getInstance().login("stefanielj", "510215");
        
        String coToken = result.getToken();
        
        List<Program> programs = ProgramAPI.getInstance().getProgramsByOwner(coToken, "stefanielj");

        if (programs == null || programs.size() <= 0) {
            return;
        }
        
        for (Program program : programs) {
            ProgramAPI.getInstance().deleteProgramById(coToken, program.getId());
        }
    }
}
