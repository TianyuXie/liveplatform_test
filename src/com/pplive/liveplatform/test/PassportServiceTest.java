package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.service.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.passport.PassportService;

public class PassportServiceTest extends AndroidTestCase {
    
    private static final String TAG = PassportServiceTest.class.getSimpleName();

    public void testLogoinService() throws LiveHttpException {
        PassportService.getInstance().login("xiety0001", "xiety0001");

    }

    public void testGetCheckGUID() throws LiveHttpException {
        String guid = PassportService.getInstance().getCheckCodeGUID();
        
        Log.d(TAG, "guid: " + guid);
        
        String imageUrl = PassportService.getInstance().getCheckCodeImageUrl(guid);
        
        Log.d(TAG, "url: " + imageUrl);
    }
    
//    public void testRegister() throws LiveHttpException {
//        boolean ret = PassportService.getInstance().register("xiety0002", "xiety0002", "xiety0002@163.com", "fYxa", "93427bf55704f3487ead81f3696e886d");
//        
//        Log.d(TAG, "ret: " + ret);
//    }
 }
