package com.ihfazh.javascriptdemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lunarday.lunardayjs.LunarDay;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<List<LunarDay>> lunarDay = new MutableLiveData<>();
}
