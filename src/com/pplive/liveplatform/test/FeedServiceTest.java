package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.UserManager;
import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.FeedAPI;
import com.pplive.liveplatform.core.api.live.model.FallList;
import com.pplive.liveplatform.core.api.live.model.Feed;
import com.pplive.liveplatform.core.api.live.model.Feed.FeedType;

public class FeedServiceTest extends AndroidTestCase {

    static final String TAG = FeedServiceTest.class.getSimpleName();

    private String mCoToken;

    private String mUsername;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        UserManager manager = UserManager.getInstance(getContext());

        mUsername = manager.getUsernamePlain();
        mCoToken = manager.getToken();
    }

    public void testGetSystemMsg() throws LiveHttpException {

        FallList<Feed> feeds = FeedAPI.getInstance().getSystemMsgs(mCoToken, mUsername, "", 10);

        for (Feed feed : feeds.getList()) {
            Log.d(TAG, "FeedType: " + feed.getFeedType());

            if (FeedType.FOLLOW_FRIEND == feed.getFeedType()) {
                Log.d(TAG, "username: " + feed.getFans().getDisplayName());

            }
        }
    }

    public void testGetFollowCircle() throws LiveHttpException {

        FallList<Feed> feeds = FeedAPI.getInstance().getFollowCircleFeeds(mCoToken, mUsername, "", 10);

        Log.d(TAG, "count: " + feeds.count());
    }
}
