package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.SearchAPI;
import com.pplive.liveplatform.core.api.live.model.FallList;
import com.pplive.liveplatform.core.api.live.model.User;

public class SearchServiceTest extends AndroidTestCase {

    private static final String TAG = SearchServiceTest.class.getSimpleName();

    //    public void testSearch() throws LiveHttpException {
    //        FallList<Program> programs = SearchAPI.getInstance().searchProgram(1, SortKeyword.VV, LiveStatusKeyword.LIVING, "", 10);
    //
    //        for (Program program : programs.getList()) {
    //            Log.d(TAG, program.toString());
    //        }
    //    }

    public void testSearch() throws LiveHttpException {
        FallList<User> users = SearchAPI.getInstance().searchUser("xiety0001", "", 10);

        for (User user : users.getList()) {
            Log.d(TAG, "nickname: " + user.getDisplayName());
        }
    }
}
