package com.yan.demo03;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.yan.demo03", appContext.getPackageName());
    }

    @Test
    public void test() {
        String ok = "你好我是";
        String ok1 = "fjekf，";
        String text = "ABC汉DEF";
        System.out.println("##########" + calculateLength(ok));
        System.out.println("##########" + calculateLength(ok1));
        System.out.println("##########" + calculateLength(text));

    }


    private int calculateLength(String s) {
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (charAt < 255) {
                len++;
            } else {
                len += 2;
            }
        }
        return Math.round(len);
    }

    public String substring(String text, int length)
            throws UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int currentLength = 0;
        for (char c : text.toCharArray()) {
            currentLength += String.valueOf(c).getBytes("GBK").length;
            if (currentLength <= length) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    @Test
    public void testString() throws UnsupportedEncodingException {
         String text = "ABC汉DEF";
         String ok = "你好我是你爸爸吧，知道吗小老弟";
         String ok1 = "fjekfjskjfkjdse，";

        System.out.println(new StringBuilder().append("######")
                .append(substring(text, 6))
                .toString());

        System.out.println(new StringBuilder().append("######")
                .append(substring(ok, 6))
                .toString());

        System.out.println(new StringBuilder().append("######")
                .append(substring(ok1, 6))
                .toString());

    }

    @Test
    public void ok() {
        matcherSearchTitle("你好，小妹妹", "小");
    }

    private SpannableString matcherSearchTitle(String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();

        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);

        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            Log.e("###", start +"#" +end);
            // ss.setSpan(new ForegroundColorSpan(ChatMessageSearchActivity.this.getResources().getColor(R.color.v3_common_main_tone)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    @Test
    public void testSubList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Log.e("### list:", String.valueOf(list));
        List<String> newList =  list.subList(0, 2);
        newList.add(2,"new");
        Log.e("### newList:", String.valueOf(newList));
    }

    @Test
    public void testTime() {
        Log.e("###", String.valueOf(System.currentTimeMillis()));
    }

    /**
     * 弱引用
     */
    @Test
    public void WeakReferenceTest() {
        WeakReference<Object> weakReference=new WeakReference<>(new Object());
        Log.e("###beforeGC", String.valueOf(weakReference.get()));
        List<Byte[]> list=new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add(new Byte[1024]);
        }
        Log.e("###afterGC", String.valueOf(weakReference.get()));

    }

}