package com.lunarday.lunardayjs;

import androidx.annotation.NonNull;

public class LunarDay {
    private int number;
    private String start;
    private String end;

    public LunarDay(int number, String start, String end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    public LunarDay() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @NonNull
    @Override
    public String toString() {
        return "number: " + this.number + " start: " + this.start + " end: " + this.end;
    }
}
