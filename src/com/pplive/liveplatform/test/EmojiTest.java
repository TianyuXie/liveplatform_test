package com.pplive.liveplatform.test;

import java.util.regex.Matcher;

import com.pplive.liveplatform.ui.player.Emoji;

import android.test.AndroidTestCase;
import android.util.Log;

public class EmojiTest extends AndroidTestCase {

    static final String TAG = EmojiTest.class.getSimpleName();

    String feedback = "/微笑/大小/大哭";

    public void testEmoji() {

        Log.d(TAG, convertEmojiToImageTag(feedback));
    }

    private String convertEmojiToImageTag(String content) {
        StringBuilder sb = new StringBuilder(content);

        Log.d(TAG, sb.toString());

        Matcher matcher = Emoji.REG_EMOJI.matcher(sb);

        while (matcher.find()) {
            sb.replace(matcher.start(), matcher.end(), "<img src=\"" + matcher.group(1) + "\"/>");

            matcher = Emoji.REG_EMOJI.matcher(sb);
        }

        Log.d(TAG, sb.toString());

        return sb.toString();
    }
}
