package com.pplive.liveplatform.test;

import android.test.AndroidTestCase;
import android.util.Log;
import de.greenrobot.event.EventBus;

public class EventBusTest extends AndroidTestCase {

    private static final String TAG = EventBusTest.class.getSimpleName();
    
    private EventBus mEventBus = EventBus.getDefault();
    
    public void testEventpus() {
        mEventBus.register(this);
        
        mEventBus.post(new AnyEvent());
    }
    
    public void onEvent(AnyEvent event) {
        
        Log.d(TAG, "onEvent AnyEvents");
    }
    
    class AnyEvent {
        
    }
}
