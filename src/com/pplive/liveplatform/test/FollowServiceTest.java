package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.UserManager;
import com.pplive.liveplatform.core.api.exception.LiveHttpException;
import com.pplive.liveplatform.core.api.live.FeedAPI;
import com.pplive.liveplatform.core.api.live.FollowAPI;
import com.pplive.liveplatform.core.api.live.model.FallList;
import com.pplive.liveplatform.core.api.live.model.Feed;
import com.pplive.liveplatform.core.api.live.model.UserFriendCount;

public class FollowServiceTest extends AndroidTestCase {

    static final String TAG = FollowServiceTest.class.getSimpleName();

    //    public void testGetRelations() throws LiveHttpException {
    //        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
    //
    //        List<UserRelation> relations = FollowAPI.getInstance().getRelations(result.getToken(), "xiety0001", "1,2,3,4,5");
    //
    //        for (UserRelation relation : relations) {
    //            Log.d(TAG, "id: " + relation.getId());
    //            Log.d(TAG, "username: " + relation.getUsername());
    //            Log.d(TAG, "relation: " + relation.getRelation());
    //        }
    //    }

    //    public void testFollow() throws LiveHttpException {
    //        //        LoginResult result = PassportAPI.getInstance().login("xiety0001", "xiety0001");
    //
    //        UserManager manager = UserManager.getInstance(getContext());
    //
    //        boolean ret = FollowAPI.getInstance().follow(manager.getToken(), "xiety0001", 31);
    //
    //        Log.d(TAG, "ret: " + ret);
    //    }

    public void testGetFriendCount() throws LiveHttpException {

        UserFriendCount friendCount = FollowAPI.getInstance().getUserFriendCount("xiety0001");

        Log.d(TAG, "Follows: " + friendCount.getFollowsCount());
        Log.d(TAG, "Fans: " + friendCount.getFansCount());
    }

    public void testGetFollowCicleFeeds() throws LiveHttpException {
        String coToken = UserManager.getInstance(getContext()).getToken();

        FallList<Feed> fallList = FeedAPI.getInstance().getFollowCircleFeeds(coToken, "xiety0001", "", 10);

        for (Feed feed : fallList.getList()) {
            Log.w(TAG, "FeedType: " + feed.getFeedType());
        }
    }
}
