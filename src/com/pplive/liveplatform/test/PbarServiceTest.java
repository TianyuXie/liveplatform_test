package com.pplive.liveplatform.test;

import java.util.Map;
import java.util.Map.Entry;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pplive.liveplatform.core.service.comment.PbarService;
import com.pplive.liveplatform.core.service.comment.model.Feed;
import com.pplive.liveplatform.core.service.comment.model.FeedDetailList;
import com.pplive.liveplatform.core.service.exception.LiveHttpException;

public class PbarServiceTest extends AndroidTestCase {

	private static final String TAG = PbarServiceTest.class.getSimpleName();
	
	public void testGetFeeds() throws LiveHttpException {
		
		FeedDetailList feedDetail = PbarService.getInstance().getFeeds(Constants.TEST_COTK, 3301, 30);
		
		Map<Long, Feed> feedMap = feedDetail.getFeedMap();
		
		for (Entry<Long, Feed> entry : feedMap.entrySet()) {
			Log.d(TAG, "entry key: " + entry.getKey());
		}
	}
	
	public void testPutFeed() throws LiveHttpException {
		Feed feed = new Feed(3301, "I'm hero", Feed.Type.COMMENT);
		
		long feedId = PbarService.getInstance().putFeed(Constants.TEST_COTK, feed);
		
		Log.d(TAG, "feedId: " + feedId);
	}
}
