package com.pigeonhunter.loph.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.lang.Exception

interface IGameView {

}

class GameView(context: Context, attrs: AttributeSet?, defStyle: Int): View(context, attrs, defStyle), IGameView {
    companion object {
        private const val tagName = "GameView"
        private val mPaint = Paint()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null, 0)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(tagName, MeasureSpec.toString(widthMeasureSpec))
        Log.d(tagName, MeasureSpec.toString(heightMeasureSpec))
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) throw Exception("TestView must draw with a canvas")
        canvas.drawColor(Color.CYAN)
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        canvas.drawLine(0f, 0f, width.toFloat(), height.toFloat(), mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}
