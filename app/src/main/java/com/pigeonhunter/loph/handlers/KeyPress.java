package com.pigeonhunter.loph.handlers;

public class KeyPress {
    // 用于记录应当在何时按下哪个button
    private int _rowId;
    private int _colId;
    private float _pressTime; // 倒计时时间
    public KeyPress(int rowId,int colId,float pressTime){
        _rowId = rowId;
        _colId = colId;
        _pressTime = pressTime;
    }
    public int getRowId(){
        return _rowId;
    }
    public int getColId(){
        return _colId;
    }
    public float getPressTime(){
        return  _pressTime;
    }

}
