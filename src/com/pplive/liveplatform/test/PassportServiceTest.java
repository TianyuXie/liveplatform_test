package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.passport.PassportAPI;

public class PassportServiceTest extends AndroidTestCase {

    private static final String TAG = PassportServiceTest.class.getSimpleName();

    public void testLogoinService() throws LiveHttpException {
        PassportAPI.getInstance().login("xiety0001", "xiety0001");

    }

    public void testGetCheckGUID() throws LiveHttpException {
        String guid = PassportAPI.getInstance().getCheckCodeGUID();

        Log.d(TAG, "guid: " + guid);

        String imageUrl = PassportAPI.getInstance().getCheckCodeImageUrl(guid);

        Log.d(TAG, "url: " + imageUrl);
    }

    //    public void testSendCheckCode() throws LiveHttpException {
    //        PassportService.getInstance().sendPhoneCheckCode("13524159033");
    //    }
    //
    //    public void testRegisterPhone() throws LiveHttpException {
    //        PassportService.getInstance().registerByPhoneNumSimple("13524159033", "xiety0001", "426741");
    //    }

    //    public void testRegister() throws LiveHttpException {
    //        boolean ret = PassportService.getInstance().register("xiety0002", "xiety0002", "xiety0002@163.com", "fYxa", "93427bf55704f3487ead81f3696e886d");
    //        
    //        Log.d(TAG, "ret: " + ret);
    //    }  
}
