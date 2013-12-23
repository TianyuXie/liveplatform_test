package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.UserService;
import com.pplive.liveplatform.core.service.live.model.User;

public class UserServiceTest extends AndroidTestCase {
	
	private static final String TAG = UserServiceTest.class.getSimpleName();

	public void testGetUserInfo() throws LiveHttpException {
		User user = UserService.getInstance().getUserInfo(Constants.TEST_COTK, "xiety0001");
		
		
		Log.d(TAG, "username: " + user.getUsername());
	}
	
	public void testUpdateUserInfo() throws LiveHttpException {
		User user = UserService.getInstance().getUserInfo(Constants.TEST_COTK, "xiety0001");
		
		Log.d(TAG, "username: " + user.getUsername());
		
		user.setNickname("Tainyu_Xie");
		
		boolean ret = UserService.getInstance().updateOrCreateUser(Constants.TEST_COTK, user);
		
		Log.d(TAG, "ret: " + ret);
	}
}
