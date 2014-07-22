package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.live.auth.LiveTokenAuthentication;
import com.pplive.liveplatform.core.api.live.auth.PlayTokenAuthentication;
import com.pplive.liveplatform.core.api.live.auth.UserTokenAuthentication;

public class TokenAuthenticationTest extends AndroidTestCase {

    private static final String TAG = TokenAuthenticationTest.class.getSimpleName();

    public void testTokenAuthenticationTest() {
        UserTokenAuthentication auth = new UserTokenAuthentication(Constants.TEST_COTK);

        Log.d(TAG, "auth: " + auth.getHeaderValue());

        auth = new PlayTokenAuthentication(Constants.TEST_COTK, "playToken");

        Log.d(TAG, "auth: " + auth.getHeaderValue());

        auth = new LiveTokenAuthentication(Constants.TEST_COTK, "liveToken");
        
        Log.d(TAG, "auth: " + auth.getHeaderValue());
    }
}
