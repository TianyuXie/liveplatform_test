package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.service.exception.LiveHttpException;
import com.pplive.liveplatform.core.service.live.SearchService;
import com.pplive.liveplatform.core.service.live.SearchService.LiveStatusKeyword;
import com.pplive.liveplatform.core.service.live.SearchService.SortKeyword;
import com.pplive.liveplatform.core.service.live.model.FallList;
import com.pplive.liveplatform.core.service.live.model.Program;

public class SearchServiceTest extends AndroidTestCase {

    private static final String TAG = SearchServiceTest.class.getSimpleName();

    public void testSearch() throws LiveHttpException {
        FallList<Program> programs = SearchService.getInstance().searchProgram(1, SortKeyword.VV, LiveStatusKeyword.LIVING, "", 10);

        for (Program program : programs.getList()) {
            Log.d(TAG, program.toString());
        }
    }
}
