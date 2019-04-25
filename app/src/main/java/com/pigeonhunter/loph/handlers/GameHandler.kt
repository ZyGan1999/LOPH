package com.pigeonhunter.loph.handlers

import android.os.Handler
import android.os.Message

class GameHandler: Handler() {
    companion object {
        const val GAME_LOAD = 0
        const val GAME_START = 1
        const val GAME_PAUSE = 2
        const val GAME_END = 3
        const val GAME_TERMINATE = 4
        const val GAME_OPERATE_FINGER_DOWN = 5
        const val GAME_OPERATE_FINGER_MOVE = 6
        const val GAME_OPERATE_FINGER_UP = 7
    }

    override fun handleMessage(msg: Message?) {

    }
}