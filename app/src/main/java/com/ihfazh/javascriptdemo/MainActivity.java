package com.ihfazh.javascriptdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;


import com.lunarday.lunardayjs.LunarDay;
import com.lunarday.lunardayjs.LunarJS;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainActivityViewModel.class);

        WebView webView = findViewById(R.id.webView);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.YEAR, 2020);

        Date date = cal.getTime();

        new LunarJS(webView).lunarDays(
                date,
                55.755826,
                37.617300,
                new LunarJS.LunarDayCallback() {
                    public void onResultReceived(List<LunarDay> results) {
                        viewModel.lunarDay.setValue(results);
                        System.out.println(results);
                    }
                }
        );

        TextView tvResult = findViewById(R.id.tvResult);

        viewModel.lunarDay.observe(this, results -> {
            StringBuilder builder = new StringBuilder();
            for (LunarDay lunarDay : results) {
                builder.append(lunarDay.toString()).append("\n");
            }

            String result = builder.toString();
            tvResult.setText(result);
        });
    }
}