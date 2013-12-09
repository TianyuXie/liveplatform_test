package com.pplive.liveplatform.test;

import com.pplive.liveplatform.core.service.passport.PassportService;

import android.test.AndroidTestCase;

public class PassportServiceTest extends AndroidTestCase  {

    public void testLogoinService() throws Exception {
        PassportService.getInstance().login("xiety0001", "xiety0001");
    }
}
