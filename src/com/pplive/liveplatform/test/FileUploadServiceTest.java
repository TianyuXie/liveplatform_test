package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.FileUploadService;
import com.pplive.liveplatform.core.service.passport.PassportService;
import com.pplive.liveplatform.core.service.passport.model.LoginResult;

public class FileUploadServiceTest extends AndroidTestCase {

    public void testUploadFile() throws LiveHttpException {
        LoginResult result = PassportService.getInstance().login("xiety0001", "xiety0001");
        
        FileUploadService.getInstance().uploadFile(result.getToken(), "xiety0001", "/sdcard/ppbox.conf");
    }
}
