package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.FileUploadAPI;
import com.pplive.liveplatform.core.api.passport.PassportAPI;
import com.pplive.liveplatform.core.api.passport.model.LoginResult;

public class FileUploadServiceTest extends AndroidTestCase {

    public void testUploadFile() throws LiveHttpException {
        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
        
        FileUploadAPI.getInstance().uploadFile(result.getToken(), "xiety0001", "/sdcard/ppbox.conf");
    }
}
