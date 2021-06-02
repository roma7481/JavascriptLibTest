package com.lunarday.lunardayjs;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LunarJS {
    private final WebView mWebView;

    public LunarJS(WebView webView) {
        mWebView = webView;

        initWebView(mWebView);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(WebView webView) {
        // force to enable the javascript
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public void lunarDays(Date date, double latitude, double longitude, @Nullable LunarDayCallback callback){
        String datePattern = "yyyy-MM-dd";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(datePattern);
        String formattedDate = format.format(date);
        this.lunarDays(formattedDate, latitude, longitude, callback);
    }

    public void lunarDays(String date, double latitude, double longitude, @Nullable LunarDayCallback callback) {
        mWebView.loadUrl("file:///android_asset/index.html");
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                @SuppressLint("DefaultLocale") String script = String.format("lunarDayConverter(lunarDays('%s', %f, %f))", date, latitude, longitude);
                mWebView.evaluateJavascript(script,
                        value -> {
                            if (callback != null){
                                try {
                                    callback.onResultReceived(
                                            callback.processResult(value)
                                    );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                );
            }
        });
    }

    public static abstract class LunarDayCallback {
        public void onResultReceived(List<LunarDay> results){
        }

        public List<LunarDay> processResult(String result) throws JSONException {
            ArrayList<LunarDay> resultList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                resultList.add(
                        new LunarDay(
                                object.getInt("number"),
                                object.getString("start"),
                                object.getString("end")
                        )
                );

            }
            return resultList;
        }

    }
}
