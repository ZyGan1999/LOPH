package com.pigeonhunter.loph.handlers;

import android.util.Log;

public class KeyPress {
    // 用于记录应当在何时按下哪个button
    private int _rowId;
    private int _colId;
    private long _pressTime; // 倒计时时间
    private static final String TAG = "KeyPress";
    public KeyPress(int rowId, int colId, long pressTime){
        _rowId = rowId;
        _colId = colId;
        _pressTime = pressTime;
    }
    public KeyPress(int rowId, int colId) {
        _rowId = rowId;
        _colId = colId;
        _pressTime = -1;
    }
    public int getRowId(){
        return _rowId;
    }
    public int getColId(){
        return _colId;
    }
    public long getPressTime(){
        return  _pressTime;
    }
    public void set_rowId(int id){
        _rowId = id;
    }
    public void set_colId(int id){
        _colId = id;
    }
    public void set_pressTime(long pt){
        _pressTime = pt;
    }

    public boolean compareAccuracy(KeyPress kp){
        if (kp._rowId != this._rowId || kp._colId != this._colId){
            return false;
        }
        else return (Math.abs(kp._pressTime - this._pressTime) <= 100);
    }

    public void log(){
        Log.e(TAG, "rowID:"+_rowId+" "+_colId+" "+_pressTime);
    }

}
